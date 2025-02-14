package org.example;

import org.example.bean.*;
import org.example.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        /*SpringBean bean = context.getBean(SpringBean.class);
        System.out.println(bean);
        //bean.sayHello();
        TestBean1 testBean1 = context.getBean(TestBean1.class);
        System.out.println(testBean1);
        TestBean2 testBean2 = context.getBean(TestBean2.class);
        System.out.println(testBean2);*/

        //context.close();

       /* Runtime.getRuntime().addShutdownHook(new Thread() { //jvm eka shout down welawa allagena e athule context eka close kala
            public void run() {
                System.out.println("Shutting down...");
                context.close();
            }
        });*/
        //Object bean = context.getBean("springBean");
       /* Object bean = context.getBean("bean");
        System.out.println(bean);

        TestBean1 bean1 = (TestBean1) context.getBean("testBean1");
        System.out.println(bean1);
        TestBean2 bean2 = (TestBean2) context.getBean("testBean2");
        System.out.println(bean2);*/

       /* MyConnection myConnection = (MyConnection) context.getBean(MyConnection.class);
        System.out.println(myConnection);*/

        /*MyConnection myConnection = (MyConnection) context.getBean("bean2");
        System.out.println(myConnection);*/

        /*MyConnection myConnection = (MyConnection) context.getBean("myConnection");
        System.out.println(myConnection);*/

        TestBean1 ref1 = context.getBean(TestBean1.class);
        System.out.println(ref1);
        TestBean1 ref2 = context.getBean(TestBean1.class);
        System.out.println(ref2);
        TestBean1 ref3 = context.getBean(TestBean1.class);
        System.out.println(ref3); //singleton nisa ekama object thamai use wenne / ekama reference eka

        MyConnection ref4 = context.getBean(MyConnection.class);
        System.out.println(ref4);
        MyConnection ref5 = context.getBean(MyConnection.class);
        System.out.println(ref5);



        context.registerShutdownHook(); //uda thread eka wenuwata
       /* TestBean3 testBean3 = context.getBean(TestBean3.class);
        System.out.println(testBean3);*/




    }
}
