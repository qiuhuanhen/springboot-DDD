package com.qiuhuanhen.springroot.interfaces.controller.ControllerAdvice;

import com.qiuhuanhen.springroot.application.bo.PeopleBO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
* Description: RestControllerAdvice的作用测试<br/>
* date: 2021/12/6 23:02<br/>
* @author qkj <br/>
* @since JDK 1.8
*/

@RestController
@RequestMapping("/advice/test")
public class ControllerAdviceTestController {
    /** 测试全局捕获空指针异常是否正常 **/
    @GetMapping("/null")
    public void testNullException() {
        PeopleBO people = new PeopleBO();
        people.setName(null);
        people.getName().equals("xxx");

    }

    /**  测试获取全局绑定的数据 **/
    @GetMapping("/model")
    public String getModel(Model model) {
        Map<String, Object> map = model.asMap();
        return (String) map.get("string");
    }


}
