package com.foodordering.config;


import com.foodordering.commons.Database;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.foodordering"})
@EnableTransactionManagement
public class HibernateConfig {

    @Autowired
    private Database database;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName(database.getDriver());
        dataSource.setUrl(database.getUrl());
        dataSource.setUsername(database.getUsername());
        dataSource.setPassword(database.getPassword());
        System.out.println("????????????????????????//?????????????????????????mynameisakbarkhan");
        return dataSource;
    }

    @Bean
    public SessionFactory getSessionfactory(DataSource dataSource) {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
        builder.addProperties(getHibernateProperties());
        builder.scanPackages("com.foodordering");
        return builder.buildSessionFactory();

    }

    private Properties getHibernateProperties() {

        Properties properties = new Properties();

        properties.put("hibernate.dialect", database.getDialect());
        return properties;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
    {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }
}