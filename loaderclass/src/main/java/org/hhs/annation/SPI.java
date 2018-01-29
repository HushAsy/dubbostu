package org.hhs.annation;

import com.sun.glass.ui.CommonDialogs;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SPI {
    String value() default "";
}
