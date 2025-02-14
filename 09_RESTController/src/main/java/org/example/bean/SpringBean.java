package org.example.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringBean{
    public SpringBean() {
        System.out.println("Spring Bean Constructor");
    }
}
