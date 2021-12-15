package com.qiuhuanhen.springroot.infrastructure.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

/**
 *  定时分布锁配置
 */
@Configuration
@EnableScheduling
public class ScheduledTaskConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public LockProvider lockProvider() {
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        DataSource db2 = ds.getDataSource("db2");
        return new JdbcTemplateLockProvider(
                JdbcTemplateLockProvider.Configuration.builder()
                        .withJdbcTemplate(new JdbcTemplate(db2))
                        //.withTimeZone(TimeZone.getTimeZone("UTC"))
                        .withTableName("sys_schedule_lock")
                        .build()
        );
    }
}
