package org.example;

import org.example.bean.TestBean1;
import org.example.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
/*
        TestBean1 testBean1 = context.getBean(TestBean1.class);
        System.out.println(testBean1);

        TestBean1 testBean2 = context.getBean(TestBean1.class);
        System.out.println(testBean2);*/

      context.registerShutdownHook();

    }



}
