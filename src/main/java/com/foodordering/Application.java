package com.foodordering;

import com.foodordering.commons.Database;
import com.foodordering.config.JwtFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class Application implements CommandLineRunner{

    @Autowired
    private Database database;

    public static void main(String[] args) {

        SpringApplication.run(Application.class);
        String url="http://localhost:8080/user/sum/12/12";
        RestTemplate restTemplate=new RestTemplate();
        int sum=restTemplate.getForObject(url,Integer.class);
        System.out.println("Sum :"+sum);

    }

    public void run(String... args) throws Exception {
        System.out.println("driver : " + database.getDriver());
        System.out.println("url: " + database.getUrl());
        System.out.println("username: " + database.getUsername());
        System.out.println("password: " + database.getPassword());
        System.out.println("dialect: " + database.getDialect());
//        System.out.println("hbm_auto_ddl: " + database.getHibernateDDlAuto());
    }
    @Bean
    public FilterRegistrationBean jwtFilter(){
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/rest/*");
        return registrationBean;
    }
}
