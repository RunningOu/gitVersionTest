package com.IpManage.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baidu.boot.disconf.ConfigureProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * Created with IntelliJ IDEA.
 * User: yangfei
 * Date: 2016, 2016/4/16 11:09
 * desc：druid config
 */
@Configuration
@Component
public class DruidConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private String dbUrl;

    private String username;

    private String password;

    private String driverClassName;

    private String initialSize;

    private String minIdle;

    private String maxActive;

    private String maxWait;

    private String timeBetweenEvictionRunsMillis;

    private String minEvictableIdleTimeMillis;

    private String validationQuery;

    private String testWhileIdle;

    private String testOnBorrow;

    private String testOnReturn;

    private String poolPreparedStatements;

    private String filters;


    @Bean
    @Primary
    public DataSource druidDataSource() {

        dbUrl = (String) ConfigureProperties.getInstance().get("spring.datasource.url");
        username = (String) ConfigureProperties.getInstance().get("spring.datasource.username");
        password = (String) ConfigureProperties.getInstance().get("spring.datasource.password");
        driverClassName = (String) ConfigureProperties.getInstance().get("spring.datasource.driver.class.name");
        initialSize = (String) ConfigureProperties.getInstance().get("spring.datasource.initialSize");
        minIdle = (String) ConfigureProperties.getInstance().get("spring.datasource.minIdle");
        maxActive = (String) ConfigureProperties.getInstance().get("spring.datasource.maxActive");
        maxWait = (String) ConfigureProperties.getInstance().get("spring.datasource.maxWait");
        timeBetweenEvictionRunsMillis = (String) ConfigureProperties.getInstance().get("spring.datasource.timeBetweenEvictionRunsMillis");
        minEvictableIdleTimeMillis = (String) ConfigureProperties.getInstance().get("spring.datasource.minEvictableIdleTimeMillis");
        validationQuery = (String) ConfigureProperties.getInstance().get("spring.datasource.validationQuery");
        testWhileIdle = (String) ConfigureProperties.getInstance().get("spring.datasource.testWhileIdle");
        testOnBorrow = (String) ConfigureProperties.getInstance().get("spring.datasource.testOnBorrow");
        testOnReturn = (String) ConfigureProperties.getInstance().get("spring.datasource.testOnReturn");
        poolPreparedStatements = (String) ConfigureProperties.getInstance().get("spring.datasource.poolPreparedStatements");
        filters = (String) ConfigureProperties.getInstance().get("spring.datasource.filters");
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(Integer.valueOf(initialSize));
        datasource.setMinIdle(Integer.valueOf(minIdle));
        datasource.setMaxActive(Integer.valueOf(maxActive));
        datasource.setMaxWait(Integer.valueOf(maxWait));
        datasource.setTimeBetweenEvictionRunsMillis(Integer.valueOf(timeBetweenEvictionRunsMillis));
        datasource.setMinEvictableIdleTimeMillis(Integer.valueOf(minEvictableIdleTimeMillis));
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(Boolean.valueOf(testWhileIdle));
        datasource.setTestOnBorrow(Boolean.valueOf(testOnBorrow));
        datasource.setTestOnReturn(Boolean.valueOf(testOnReturn));
        datasource.setPoolPreparedStatements(Boolean.valueOf(poolPreparedStatements));
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        return datasource;
    }

}