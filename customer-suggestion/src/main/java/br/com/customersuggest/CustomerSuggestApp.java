package br.com.customersuggest;

import br.com.customersuggest.store.InterceptorHibernateImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@ComponentScan("br.com.customersuggest")
@RefreshScope // to allow refresh its configuration from configserver
@EnableAspectJAutoProxy
@EnableConfigurationProperties
@ConfigurationProperties(value = "spring")
public class CustomerSuggestApp {

    private Logger log = LoggerFactory.getLogger(CustomerSuggestApp.class);

    public static void main(String ... args) {
        SpringApplication.run(CustomerSuggestApp.class, args);
    }

    @Autowired
    InterceptorHibernateImpl hibernateInterceptor;

    @Bean
    @Autowired
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder factory, DataSource dataSource,
                                                                       JpaProperties jpaProperties) {

        Map<String, Object> _jpaProperties = new HashMap<>();
        _jpaProperties.putAll(jpaProperties.getProperties());
        _jpaProperties.putAll(jpaProperties.getHibernateProperties(new HibernateSettings()));
        _jpaProperties.put("hibernate.ejb.interceptor", hibernateInterceptor);

        return factory.dataSource(dataSource)
                .packages("br.com.customersuggest")
                .properties(_jpaProperties).build();
    }
}
