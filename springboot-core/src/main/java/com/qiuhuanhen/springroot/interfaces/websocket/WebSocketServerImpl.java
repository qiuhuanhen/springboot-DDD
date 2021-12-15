package com.qiuhuanhen.springroot.interfaces.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.qiuhuanhen.dto.MessageDTO;
import com.qiuhuanhen.springroot.infrastructure.config.ServerEncoder;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ws demo
 * @author qkj
 */

@ServerEndpoint(value = "/imserver/{userId}",encoders = {ServerEncoder.class})

@Component
public class WebSocketServerImpl {

    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static ConcurrentHashMap<String,WebSocketServerImpl> webSocketMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String,Session> sessionMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private static Session session;
    /**接收userId*/
    private volatile static String userId="";

    private static Object getField(Object obj, Class clazz, String fieldName) {
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field field;

                field = clazz.getDeclaredField(fieldName);

                field.setAccessible(true);

                return field.get(obj);

            } catch (Exception e) {
            }

        }
        return null;
    }

    private static Object getFieldInstance(Object obj, String fieldPath) {
        String fields[] = fieldPath.split("#");

        for (String field : fields) {
            obj = getField(obj, obj.getClass(), field);

            if (obj == null) {
                return null;
            }
        }
        return obj;
    }


    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("userId") String userId) {
        RemoteEndpoint.Async async = session.getAsyncRemote();
        InetSocketAddress addr = (InetSocketAddress) getFieldInstance(async,"base#socketWrapper#socket#sc#remoteAddress");


        WebSocketServerImpl.session = session;
        session.setMaxIdleTimeout(10000);
        WebSocketServerImpl.userId =userId;
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            webSocketMap.put(userId,this);
            //加入set中
        }else{
            webSocketMap.put(userId,this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }

        System.out.println("用户连接:"+userId+",当前在线人数为:" + getOnlineCount());

        try {
            sendMessage("连接成功");
        } catch (IOException | EncodeException e) {
            System.out.println("用户:"+userId+",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();
        }

        System.out.println("用户退出:"+userId+",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {

        System.out.println("用户消息:"+userId+",报文:"+message);
        //可以群发消息
        //消息保存到数据库、redis
        if(StringUtils.isNotBlank(message)){
            try {
                //解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                //追加发送人(防止串改)
                jsonObject.put("fromUserId", userId);
                String toUserId=jsonObject.getString("toUserId");
                //传送给对应toUserId用户的websocket
                if(StringUtils.isNotBlank(toUserId)&&webSocketMap.containsKey(toUserId)){
                    webSocketMap.get(toUserId).sendMessage(jsonObject.toJSONString());
                }else{
                    System.out.println("请求的userId:"+toUserId+"不在该服务器上");
                    //否则不在这个服务器上，发送到mysql或者redis
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("用户错误:"+ userId+",原因:"+error.getMessage());
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException, EncodeException {
        System.out.println(WebSocketServerImpl.userId);
//        this.session.getBasicRemote().sendText(message);
        MessageDTO msg = new MessageDTO();
        msg.setMessageContent(message);
        session.getBasicRemote().sendObject(msg);
    }


    /**
     * 发送自定义消息
     * */
    public static void sendInfo(String message,@PathParam("userId") String userId) throws Exception {

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                MessageDTO msg = new MessageDTO();
                msg.setMessageContent(message);
                System.out.println("发送消息到:"+userId+"，报文:"+message);
                if(StringUtils.isNotBlank(userId)&&webSocketMap.containsKey(userId)){
                    try {
                        webSocketMap.get(userId).sendMessage(message);
                    } catch (IOException | EncodeException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("用户"+userId+",不在线！");
                }
            }
        };
        timer.schedule(timerTask,1000,100);

    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServerImpl.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServerImpl.onlineCount--;
    }
}
