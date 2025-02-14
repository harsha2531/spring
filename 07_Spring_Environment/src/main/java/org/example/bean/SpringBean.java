package org.example.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringBean implements InitializingBean {

    @Value("${os.name}")
    private String osName;

    @Value("${USERNAME}")
    private String username;

    @Value("${db.user}")
    private String dbUser;

    @Value("${db.password}")
    private String dbPassword;

    public SpringBean() {
        System.out.println("Spring Bean Constructor");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("OS Name: " + osName);
        System.out.println("USERNAME: " + username);
        System.out.println("DB USER: " + dbUser);
        System.out.println("DB PASSWORD: " + dbPassword);
    }
}
