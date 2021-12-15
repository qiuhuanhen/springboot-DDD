package com.qiuhuanhen.springroot.interfaces.controller.springbase;

import com.qiuhuanhen.dto.ImportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* Description: import方式织入bean <br/>
* date: 2021/12/6 23:18<br/>
* @author qkj <br/>
* @since JDK 1.8
*/
@RestController
@RequestMapping("/import")
// 将bean加入到spring ioc
@Import({ImportDTO.class})
public class ImportController {

    @Autowired
    private ImportDTO importDTO;

    @GetMapping
    public Integer test() {
        int age = importDTO.getAge();
        return age;
    }
}
