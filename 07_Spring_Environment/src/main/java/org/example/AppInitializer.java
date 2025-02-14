package org.example;

import org.example.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.Properties;

public class AppInitializer {
    public static void main(String[] args) {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
            context.register(AppConfig.class);
            context.refresh();

            //System environment variables

            /*Map<String,String> env = System.getenv();
            for (String key : env.keySet()) {
                System.out.println(key + " = " + env.get(key));
            }
        System.out.println(env.get("HOMEPATH"));*/


            //Access Predefined Properties from Java

         /*   Properties properties = System.getProperties();
            for (String  key  : properties.stringPropertyNames()) {
                System.out.println( key + "=" + properties.getProperty(key));
            }
            System.out.println(System.getProperty("java.vm.specification.version"));*/




            context.registerShutdownHook();
        }
    }
