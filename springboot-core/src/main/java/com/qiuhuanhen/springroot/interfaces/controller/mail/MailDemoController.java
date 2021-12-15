package com.qiuhuanhen.springroot.interfaces.controller.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 邮件发送<br/>
 * yml里面需要配置
 * 如QQ邮箱 需要在QQ邮箱里面配置 开启权限
 * date: 2021/12/6 23:17<br/>
 *
 * @author qkj <br/>
 * @since JDK 1.8
 */
@RequestMapping("/mail")
@RestController
public class MailDemoController {

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping
    public String senderMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        // 发件人
        message.setFrom("1005738053@qq.com");
        // 接收人
        message.setTo(new String[]{"330237016@qq.com"});

        //邮件标题
        message.setSubject("hello");

        //邮件内容
        message.setText("world");

        javaMailSender.send(message);

        return "success";
    }
}
