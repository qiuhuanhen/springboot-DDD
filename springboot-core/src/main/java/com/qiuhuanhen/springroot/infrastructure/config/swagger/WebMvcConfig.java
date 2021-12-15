package com.qiuhuanhen.springroot.infrastructure.config.swagger;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Description: swagger config
 * date: 2021/10/21
 */

@Configuration
/**
 * @EnableWebMvc + extends WebMvcConfigurerAdapter 会使springboot的EnableAutoConfiguration 失效 ，等于继承重写
 */
//@EnableWebMvc

public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 配置可以被跨域的路径，可以任意配置，可以具体到直接请求路径。
                // 允许所有的请求方法访问该跨域资源服务器，如：POST、GET、PUT、DELETE等。
                .allowedMethods("*")
                // 允许所有的请求域名访问我们的跨域资源，可以固定单条或者多条内容，
                // 如："http://www.baidu.com"，只有百度可以访问我们的跨域资源。
                .allowedOrigins("*")
                // 允许所有的请求header访问，可以自定义设置任意请求头信息，如："X-YAUTH-TOKEN"
                .allowedHeaders("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("swagger-ui.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }
}
