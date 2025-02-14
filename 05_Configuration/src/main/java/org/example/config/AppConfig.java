package org.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

@Configuration
//@ComponentScan(basePackages = "org.example.bean")
@Import({AppConfig1.class})
//root
//@ImportResource("classpath:abc.xml")
//if not
//@ImportResource("file:absolute-path-of-abc.xml")
public class AppConfig {
}
