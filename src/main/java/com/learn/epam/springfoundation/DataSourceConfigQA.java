package com.learn.epam.springfoundation;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("QA")
public class DataSourceConfigQA {

    @Value("${qa.datasource.url}")
    private String dataSourceUrl;

    @Value("${qa.datasource.username}")
    private String dataSourceUsername;

    @Value("${qa.datasource.password}")
    private String dataSourcePassword;

    @Bean
    @Qualifier("qaDataSource")
    public DataSource qaDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl(dataSourceUrl);
        dataSource.setUsername(dataSourceUsername);
        dataSource.setPassword(dataSourcePassword);
        return dataSource;
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer() {
        return hibernateProperties -> hibernateProperties.put(
                "hibernate.dialect", "org.hibernate.dialect.MySQLDialect"
        );
    }
}

