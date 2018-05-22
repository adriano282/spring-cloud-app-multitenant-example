package br.com.zuulservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableZuulProxy
@RefreshScope
@ComponentScan(basePackages = "br.com.zuulservice")
public class ZuulServiceApp {

    public static void main(String ... args) {
        SpringApplication.run(ZuulServiceApp.class, args);
    }
}
