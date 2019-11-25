package com.oakware.common;

import com.oakware.common.persistence.JdbcEventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = {"classpath:/jdbc-memory.properties"})
public class DbUnitTestContext {
    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource bean = new DriverManagerDataSource();
        bean.setDriverClassName(environment.getProperty("database.driverClassName"));
        bean.setUrl(environment.getProperty("database.url"));
        bean.setUsername(environment.getProperty("database.username"));
        bean.setPassword(environment.getProperty("database.password"));
        return bean;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager(dataSource());
        return txManager;
    }

    @Bean
    public JdbcEventStore eventStore() {
        return new JdbcEventStore(jdbcTemplate());
    }
}
