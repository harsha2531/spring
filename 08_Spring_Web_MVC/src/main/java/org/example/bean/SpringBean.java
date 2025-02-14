package org.example.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SpringBean implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean, ApplicationContextAware {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("SpringBean SetBeanFactory");

    }

    @Override
    public void setBeanName(String name) {
        System.out.println("SpringBean SetBeanName");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("SpringBean Destroy");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("SpringBean AfterPropertiesSet");

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("SpringBean SetApplicationContext");

    }

    public SpringBean() {
        System.out.println("Spring Bean Constructor");
    }
}
