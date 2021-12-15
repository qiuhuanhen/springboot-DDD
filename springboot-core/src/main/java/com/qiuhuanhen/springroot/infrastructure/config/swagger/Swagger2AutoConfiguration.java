package com.qiuhuanhen.springroot.infrastructure.config.swagger;


import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Description: SwaggerConfig
 * date: 2021/10/20
 */
// 开启Swagger
@EnableSwagger2
// 声名为配置类
@Configuration
// 开启Knife4j的扩展功能
@EnableKnife4j
// 导入Swagger属性类
@EnableConfigurationProperties(Swagger2Properties.class)
@Import(BeanValidatorPluginsConfiguration.class)

public class Swagger2AutoConfiguration {

    @Bean
    public Docket buildDocket(Swagger2Properties swagger2Properties) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .select()
                //要扫描的API(Controller)基础包
                // .apis(RequestHandlerSelectors.basePackage(basePackageName))
                //控制暴露出去的路径下的实例,如果某个接口不想暴露,可以使用注解@ApiIgnore,这样该接口就不会暴露在 swagger2 的页面下
                // .apis(RequestHandlerSelectors.any()) 将任何路径接口都暴露 不推荐 会暴露oauth等
                .apis(apis(swagger2Properties))
                .paths(paths(swagger2Properties))
                .build();
    }

    private Predicate<RequestHandler> apis(Swagger2Properties swagger2Properties) {
        List<Predicate<RequestHandler>> basePackages = new LinkedList<>();
        if (swagger2Properties.getBasePackage().isEmpty()) {
            basePackages.add((Predicate<RequestHandler>) RequestHandlerSelectors.any());
        }
        // 支持多个路径 配置文件中用逗号隔开
        for (String basePackage : swagger2Properties.getBasePackage()) {
            basePackages.add((Predicate<RequestHandler>) RequestHandlerSelectors.basePackage(basePackage));
        }
        return Predicates.or(basePackages);
    }

    private Predicate<String> paths(Swagger2Properties swagger2Properties) {

        List<Predicate<String>> basePaths = new ArrayList<>();

        if (swagger2Properties.getBasePath().isEmpty()) {
            basePaths.add(PathSelectors.any());
        }

        for (String basePath : swagger2Properties.getBasePath()) {
            basePaths.add(PathSelectors.ant(basePath));
        }


        List<Predicate<String>> excludePaths = new ArrayList<>();
        for (String excludePath : swagger2Properties.getExcludePath()) {
            excludePaths.add( PathSelectors.ant(excludePath));
        }

        return Predicates.and(
                Predicates.not(
                        Predicates.or(excludePaths)
                ),
                Predicates.or(basePaths)
        );
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("服务API描述文档") //文档标题
                .description("服务中心API描述文档")//接口概述
                .version("1.0") //版本号
                .termsOfServiceUrl(String.format("url"))//服务的域名
                .license("LICENSE")//证书
                .build();
    }
}
