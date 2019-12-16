package com.emergon.config;

/*
References:
https://www.javaspringclub.com/spring-mvc-hibernate-mysql-example/
https://www.baeldung.com/transaction-configuration-with-jpa-and-spring Transactions with Spring
https://www.journaldev.com/21440/spring-propertysource @PropertySource example

Optional:
https://www.mkyong.com/spring/spring-propertysources-example/ @PropertySource more info
 */
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement// Used to enable transactional support: Annotations can be recognized by spring container
@PropertySource(value = {"classpath:application.properties"}) //Externalize your configuration to a properties file
public class DatabaseConfig {

    @Autowired
    private Environment environment;//Can be used to read properties file
    /*
    Once the SessionFactory is created, it will be injected into Bean method transactionManager 
    which may eventually provide transaction support for the sessions created by this sessionFactory.
    */
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }

    @Bean
    public DataSource dataSource() {//Declare information of DB connection by using a Datasource
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driver"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;
    }
    
    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.emergon.entities");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
}
