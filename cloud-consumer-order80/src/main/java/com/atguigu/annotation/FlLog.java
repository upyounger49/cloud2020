package com.atguigu.annotation;

import sun.awt.SunHints;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface FlLog {
    String value() default "";
}
