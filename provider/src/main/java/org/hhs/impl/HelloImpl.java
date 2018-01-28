package org.hhs.impl;

import org.hhs.inter.Hello;

public class HelloImpl implements Hello{
    public String helloWorld(String name) {
        System.out.println("provider:"+name);
        return "hello:"+name;
    }
}
