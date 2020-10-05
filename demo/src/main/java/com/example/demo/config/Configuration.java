package com.example.demo.config;

import javax.sql.DataSource;

import com.blade.Blade;
import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Order;
import com.blade.loader.BladeLoader;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
@Bean
@Order(1)
public class Configuration implements BladeLoader {
    private CachingConnectionFactory cFactory;
    private String exchangeName;
    private String routingKey;
    private DriverManagerDataSource dataSource;
    //for transaction management
    private PlatformTransactionManager ptmng;
    @Override
    public void load(Blade blade) {
        /* 
        Configuration of CAching connection Factory */
        exchangeName="demo-exchange";
        routingKey="demo-key";

        cFactory  = new CachingConnectionFactory();
        cFactory.setUsername(blade.environment().getOrNull("spring.rabbitmq.username"));
        cFactory.setPassword(blade.environment().getOrNull("spring.rabbitmq.password"));
        cFactory.setPort(Integer.parseInt(blade.environment().getOrNull("spring.rabbitmq.port")));
        cFactory.setAddresses(blade.environment().getOrNull("spring.rabbitmq.host"));

        dataSource = new DriverManagerDataSource();
        /* 
        Configation of data source for db connection */
        dataSource.setDriverClassName(blade.environment().getOrNull("spring.datasource.driver-class-name"));
        dataSource.setUrl(blade.environment().getOrNull("spring.datasource.url"));
        dataSource.setUsername(blade.environment().getOrNull("spring.datasource.username"));
        dataSource.setPassword(blade.environment().getOrNull("spring.datasource.password"));

        /* 
        Configuration of PlatforTransactioManager */
        ptmng = new DataSourceTransactionManager(dataSource);



    }

    public CachingConnectionFactory getCFactory(){
        return this.cFactory;
    }

    public String getExchange(){
        return this.exchangeName;
    }

    public String getRouStringKey(){
        return this.routingKey;
    }

    public DataSource getDriverManagerDataSource(){
        return this.dataSource;
    }
    public PlatformTransactionManager getPlatformtransactionManager(){
        return this.ptmng;
    }


    
}