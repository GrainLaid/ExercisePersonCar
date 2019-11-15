package net.exercise.config;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import static org.hibernate.cfg.Environment.*;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("net.exercise.DAO"),
        @ComponentScan("net.exercise.service") })
public class HibernateConfig {

    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        Properties props = new Properties();
        props.put(DRIVER, environment.getRequiredProperty("jdbc.driverClassName"));
        props.put(URL, environment.getRequiredProperty("jdbc.url"));
        props.put(USER, environment.getRequiredProperty("jdbc.username"));
        props.put(PASS, environment.getRequiredProperty("jdbc.password"));
        //Hiber
        props.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        props.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        props.put("hibernate.connection.release_mode", environment.getRequiredProperty("hibernate.connection.release_mode"));
        props.put("current_session_context_class", environment.getRequiredProperty("current_session_context_class"));
        props.put("hibernate.connection.autoReconnect", environment.getRequiredProperty("hibernate.connection.autoReconnect"));
        props.put("hbm2ddl.auto", environment.getRequiredProperty("hbm2ddl.auto"));
        factoryBean.setHibernateProperties(props);
        factoryBean.setPackagesToScan("net.exercise.entity");
        return factoryBean;
    }
    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}
