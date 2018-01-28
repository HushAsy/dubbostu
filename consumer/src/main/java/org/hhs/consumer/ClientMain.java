package org.hhs.consumer;

import org.hhs.inter.Hello;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientMain {

    public static void main(String...args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        context.start();
        Hello hello = (Hello) context.getBean("demoService");
        String name = hello.helloWorld("mike");
        System.out.println(name);
        context.close();
    }
}
