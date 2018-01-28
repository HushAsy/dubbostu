package org.hhs.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String...args) throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        applicationContext.start();
        System.in.read();
        applicationContext.close();
    }
}
