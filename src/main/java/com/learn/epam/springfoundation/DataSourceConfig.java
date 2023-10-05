package com.learn.epam.springfoundation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSourceH2() {
        return new EmbeddedDatabaseBuilder()
                .setName("H2_1")
                .setType(EmbeddedDatabaseType.H2)
                .generateUniqueName(true)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSource conditionalDataSourceH2() {
        return new EmbeddedDatabaseBuilder()
                .setName("H2_2")
                .setType(EmbeddedDatabaseType.H2)
                .generateUniqueName(true)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .build();
    }

}