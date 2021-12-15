package com.qiuhuanhen.springroot.application.command.impl;

import com.qiuhuanhen.springroot.application.command.MessageCommandService;
import com.qiuhuanhen.springroot.interfaces.websocket.WebSocketServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.EncodeException;
import java.io.IOException;
/**
 * Description:消息增删改业务层 <br/>
 * date: 2021/12/6 20:31<br/>
 * @author qkj <br/>
 * @since JDK 1.8
 */
@Service
public class MessageCommandServiceImpl implements MessageCommandService {

    @Autowired
    private WebSocketServerImpl webSocketServer;

    @Override
    public void sendMsg() {
        try {
            webSocketServer.sendMessage("xxxxxx");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }
}
