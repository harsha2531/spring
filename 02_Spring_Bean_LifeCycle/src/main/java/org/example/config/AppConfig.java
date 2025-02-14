package org.example.config;

import org.example.bean.MyConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example.bean")
public class AppConfig {
    /*public AppConfig() {

    }*/
    @Bean
    public MyConnection getMyConnection() {
        return new MyConnection();
    }
}
