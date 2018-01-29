package org.hhs.impl;

import org.hhs.Hello;
import org.hhs.annation.SPI;

@SPI("impl")
public class HelloImpl implements Hello{
    public String sayHello() {
        System.out.println("impl");
        return "impl";
    }
}
