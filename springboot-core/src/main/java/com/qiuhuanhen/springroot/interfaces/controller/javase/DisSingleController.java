package com.qiuhuanhen.springroot.interfaces.controller.javase;

import com.qiuhuanhen.dto.HungryDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Constructor;

@RestController
@RequestMapping("/single")
public class DisSingleController {
    @RequestMapping("/test")
    public Object test() {
        try {

            Constructor<?>[] declaredConstructors = HungryDTO.class.getDeclaredConstructors();
            for (Constructor<?> declaredConstructor : declaredConstructors) {
                declaredConstructor.setAccessible(true);
                HungryDTO hungry = (HungryDTO)declaredConstructor.newInstance();
                System.out.println("00");
                HungryDTO hungry1 = (HungryDTO)declaredConstructor.newInstance();
                System.out.println(hungry==hungry1);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

}
