package com.qiuhuanhen.springroot.interfaces.websocket;

import com.qiuhuanhen.springroot.application.command.MessageCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * websocket demo
 * 打开index页面 体验websocket长连接
 */
@RestController
@Scope("prototype")
@RequestMapping("/ws")
public class WebSocketController {

    @Autowired
    private MessageCommandService messageService;

    @GetMapping("testWs")
    public String index(){
        return ("请求成功");
    }

    @GetMapping("page")
    public String page(){
        return ("websocket");
    }

    @RequestMapping("/push/{toUserId}")
    public String pushToWeb(String message, @PathVariable String toUserId) throws Exception {

        for (int i = 0; i < 10; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    messageService.sendMsg();
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }

        return ("MSG SEND SUCCESS");
    }
    @RequestMapping(value="/index", method = RequestMethod.GET)
    public ModelAndView indexPage()   {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}
