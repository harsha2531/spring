package org.example.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Boy{
    @Qualifier("girl1")
    @Autowired
    Agreement girl;
    //Girl girl;

    public Boy() {
        System.out.println("Boy Constructor");
    }

    //tight coupling
    public void chatWithGirl(){
        girl.chat();
    }
}
