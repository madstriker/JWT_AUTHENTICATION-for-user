package com.foodordering.commons;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.foodordering"})
//@PropertySource("classpath:persistence.properties")
@EnableTransactionManagement
public class HibernateTestConfig {

    @Autowired
    private Environment env;

    @Bean
    @ConfigurationProperties(prefix = "spring.test.datasource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.test.datasource.driver"));
        dataSource.setUrl(env.getProperty("spring.test.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.test.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.test.datasource.password"));
        System.out.println("mynameisakbarkhan");
        return dataSource;
    }

    @Bean
//    @Profile("test")
    public SessionFactory getSessionfactory(DataSource dataSource) {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
        builder.addProperties(getHibernateProperties());
        builder.scanPackages("com.foodordering");
        return builder.buildSessionFactory();

    }

    private Properties getHibernateProperties() {

        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getRequiredProperty("spring.test.datasource.dialect"));
        properties.put("hibernate.hbm2ddl.auto","create");
        return properties;
    }

    @Bean
//    @Profile("test")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
    {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }
}
