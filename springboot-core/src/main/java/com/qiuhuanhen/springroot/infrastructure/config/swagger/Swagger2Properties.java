package com.qiuhuanhen.springroot.infrastructure.config.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: swagger
 * date: 2021/10/20
 */
@Data

@ConfigurationProperties(prefix = "swagger")
public class Swagger2Properties {

    /**
     * 是否开启swagger
     */
    private Boolean enabled;

    /**
     * swagger解析包路径
     */
    private List<String> basePackage = new ArrayList<>();

    /**
     * swagger解析URL规则,ANT
     */
    private List<String> basePath = new ArrayList<>();

    /**
     * swagger排除解析URL规则,ANT
     */
    private List<String> excludePath = new ArrayList<>();


}
