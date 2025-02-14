package org.example.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

/*public class MyConnection  implements DisposableBean {
    public MyConnection() {
        System.out.println("MyConnection Constructor");
    }
    @Override
    public void destroy() throws Exception {
        System.out.println("MyConnection destroy");
    }
}*/

public class MyConnection implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {
    public MyConnection() {
        System.out.println("MyConnection Constructor");
    }
    @Override
    public void destroy() throws Exception {
        System.out.println("MyConnection destroy");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("MyConnection ApplicationContextAware setApplicationContext");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("MyConnection BeanNameAware setBeanName");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MyConnection afterPropertiesSet");
    }
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("MyConnection BeanFactoryAware setBeanFactory");
    }

}
