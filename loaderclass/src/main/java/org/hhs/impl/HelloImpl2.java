package org.hhs.impl;

import org.hhs.Hello;

public class HelloImpl2 implements Hello{
    @Override
    public String sayHello() {
        System.out.println("impl2");
        return "impl2";
    }
}
