package org.example.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Boy implements DI{

    //@Autowired //property injection
    Agreement girl;

    //constructor injection
   /* public Boy(Agreement girl) {
        this.girl = girl;
    }*/

    //setter method injection
   /* @Autowired
    public void setter(Agreement girl){
        this.girl = girl;
    } */

    @Autowired
    @Override
    public void inject(Agreement agreement) {
        this.girl = agreement;
    }

    public void chatWithGirl(){
        girl.chat();
    }
}
