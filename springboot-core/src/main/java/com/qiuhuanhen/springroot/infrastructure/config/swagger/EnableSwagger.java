package com.qiuhuanhen.springroot.infrastructure.config.swagger;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Description:
 * date: 2021/10/20
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Inherited
@Import({Swagger2AutoConfiguration.class,WebMvcConfig.class})
public @interface EnableSwagger {

}
