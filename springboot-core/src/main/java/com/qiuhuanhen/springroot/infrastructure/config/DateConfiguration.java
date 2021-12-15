package com.qiuhuanhen.springroot.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 全局配置时间类型转换
 */
@Configuration
public class DateConfiguration {
  /** 默认日期格式 */
  public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

  /** LocalDate转换器，用于转换RequestParam和PathVariable参数 */
  @Bean
  public Converter<String, LocalDate> localDateConverter() {
    return new Converter<String,LocalDate>() {
      @Override
      public LocalDate convert(String source) {
        return LocalDate.parse(source, DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
      }
    };
  }


}
