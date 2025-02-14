package org.example.config;

import org.example.bean.SpringBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@ComponentScan(basePackages = "org.example.di")
public class AppConfig {
    /*public AppConfig() {

    }*/

    public SpringBean getSpringBean() {
        return new SpringBean();
    }
}
