package org.example.bean;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Primary
public class Girl2 implements Agreement {

    public void chat(){
        System.out.println("Girl2 Chatting..");
    }
}
