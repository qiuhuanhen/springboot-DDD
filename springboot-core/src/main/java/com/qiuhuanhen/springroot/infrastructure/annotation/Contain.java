package com.qiuhuanhen.springroot.infrastructure.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
* Description: 自定义注解 <br/>
* date: 2021/12/6 22:44<br/>
* @author qkj <br/>
* @since JDK 1.8
*/
// 约束注解应用的目标元素类型
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
// 约束注解应用的时机
@Retention(RUNTIME)
// 与约束注解关联的验证器
@Constraint(validatedBy =ContainValidator.class )
@Documented
public @interface Contain {
    //约束注解验证时的输出消息
    String message() default "值不存在";

    // 约束注解在验证时所属的组别
    Class<?>[] groups() default {};
    // 约束注解的有效负载
    Class<? extends Payload>[] payload() default {};

    String code();
}
