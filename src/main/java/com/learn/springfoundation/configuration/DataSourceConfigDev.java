package com.learn.springfoundation.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataSourceConfigDev {

//    @Bean
//    public DataSource dataSourceH2() {
//        return new EmbeddedDatabaseBuilder()
//                .setName("H2")
//                .setType(EmbeddedDatabaseType.H2)
//                .generateUniqueName(true)
//                .setScriptEncoding("UTF-8")
//                .ignoreFailedDrops(true)
//                .build();
//    }

//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSourceH2());
//        sessionFactory.setPackagesToScan("com.learn.epam.springfoundation.configuration");
//        sessionFactory.setHibernateProperties(hibernateProperties());
//        return sessionFactory;
//    }

//    @Bean
//    public PlatformTransactionManager hibernateTransactionManager() {
//        var transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory().getObject());
//        return transactionManager;
//    }


//    @Bean
//    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer() {
//        return hibernateProperties -> hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//    }

//    private Properties hibernateProperties() {
//        Properties hibernateProperties = new Properties();
//        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
//        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//        return hibernateProperties;
//    }

}