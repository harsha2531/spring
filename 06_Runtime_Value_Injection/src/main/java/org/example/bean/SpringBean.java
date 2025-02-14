package org.example.bean;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringBean implements InitializingBean{
/*//    public SpringBean() {}

    @Autowired(required=false)
    public SpringBean(@Value("harsha-nimeda") String name,@Value("1") int id) {
        System.out.println("Spring Bean Constructor");
        System.out.println(name);
        System.out.println(id);

    }

    @Autowired(required=false)
    //dekama autowired annotated and false karama Most Parameter count eka tiyana constructor thma call wenne
    public SpringBean(@Value("harsha-nimeda") String name,@Value("1") int id,@Value("true") Boolean check) {
        System.out.println("Spring Bean Constructor");
        System.out.println(name);
        System.out.println(id);
        System.out.println(check);
    }*/

    @Value("harsha-nimeda")
    private String name;

    public SpringBean() {
        System.out.println("Spring Bean Constructor");
        System.out.println(name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(name);
    }
}
