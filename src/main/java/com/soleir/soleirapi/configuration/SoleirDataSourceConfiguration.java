package com.soleir.soleirapi.configuration;

import com.soleir.soleirapi.model.SoleirDB.Appointment;
import com.soleir.soleirapi.model.SoleirDB.SoleirUser;
import com.soleir.soleirapi.model.SoleirDB.Staff;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

//database configuration code from: https://springframework.guru/how-to-configure-multiple-data-sources-in-a-spring-boot-application/
@Configuration
@EnableTransactionManagement
//needed to define which repos, as I am using multiple datasources
@EnableJpaRepositories(
        basePackages = "com.soleir.soleirapi.repository.SoleirDB",
        entityManagerFactoryRef = "soleirEntityManagerFactory",
        transactionManagerRef = "soleirTransactionManager")
public class SoleirDataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("datasource.soleirdb")
    public DataSourceProperties soleirDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("datasource.soleirdb.configuration")
    public DataSource soleirDataSource(){
        return soleirDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name ="soleirEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean soleirEntityManagerFactory(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(soleirDataSource())
                .packages(Appointment.class, SoleirUser.class, Staff.class)
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager soleirTransactionManager(
            final @Qualifier("soleirEntityManagerFactory") LocalContainerEntityManagerFactoryBean soleirEntityManagerFactory){
        return new JpaTransactionManager(soleirEntityManagerFactory.getObject());

    }

}

