package org.hhs.impl;

import org.hhs.Hello;
import org.hhs.annation.SPI;

@SPI("impl1")
public class HelloImpl1 implements Hello{
    public String sayHello() {
        System.out.println("impl1");
        return "impl1";
    }
}
