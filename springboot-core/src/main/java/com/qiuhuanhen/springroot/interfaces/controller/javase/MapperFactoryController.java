package com.qiuhuanhen.springroot.interfaces.controller.javase;


import com.qiuhuanhen.dto.ImportDTO;
import com.qiuhuanhen.springroot.application.bo.PeopleBO;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: bean拷贝工具  <br/>
 * 功能和 beanutils.copyProperties一样 但是更强大
 * date: 2021/12/6 23:14<br/>
 *
 * @author qkj <br/>
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/mapper")
public class MapperFactoryController {
    @GetMapping
    public void mapperFactory() {

        // Import 实体类里面存在有int num 字段 ; People实体类存在 int age字段
        ImportDTO it = new ImportDTO();
        it.setNum(11);
        it.setAge(11);


        long assistStart = System.currentTimeMillis();
        DefaultMapperFactory factory = new DefaultMapperFactory.Builder().build();

        // 需要拷贝不同字段名时的处理
//        factory.classMap(Import.class, People.class).field("num", "age").byDefault().register();
        PeopleBO peopleAfter = factory.getMapperFacade().map(it, PeopleBO.class);
        long assistEnd = System.currentTimeMillis();

        System.out.println(assistEnd - assistStart);

        long beanStart = System.currentTimeMillis();
        PeopleBO people = new PeopleBO();
        BeanUtils.copyProperties(it, people);
        long beanEnd = System.currentTimeMillis();

        System.out.println(beanEnd - beanStart);

        // 此时 peopleAfter.age == it.num == 11
        System.out.println(peopleAfter);

    }
}
