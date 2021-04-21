package com.soleir.soleirapi.configuration;

import com.soleir.soleirapi.model.HospDB.AppointmentHosp;
import com.soleir.soleirapi.model.HospDB.Patient;
import com.soleir.soleirapi.model.HospDB.StaffHosp;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

//database configuration code from: https://springframework.guru/how-to-configure-multiple-data-sources-in-a-spring-boot-application/
@Configuration
@EnableTransactionManagement
//Needed to define which repos, using multiple datasources
@EnableJpaRepositories(
        basePackages = "com.soleir.soleirapi.repository.HospDB",
        entityManagerFactoryRef = "hospitalEntityManagerFactory",
        transactionManagerRef = "hospitalTransactionManager")
public class HospitalDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("datasource.hospdb")
    public DataSourceProperties hospitalDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("datasource.hospdb.configuration")
    public DataSource hospitalDataSource(){
        return hospitalDataSourceProperties().initializeDataSourceBuilder()
                .type(BasicDataSource.class).build();
    }

    @Bean (name = "hospitalEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean hospitalEntityManagerFactory(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(hospitalDataSource())
                .packages(AppointmentHosp.class, Patient.class, StaffHosp.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager hospitalTransactionManager(
            final @Qualifier("hospitalEntityManagerFactory") LocalContainerEntityManagerFactoryBean hospitalEntityManagerFactory){
        return new JpaTransactionManager(hospitalEntityManagerFactory.getObject());
    }

}
