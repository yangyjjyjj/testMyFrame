package com.yjj.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})//用于方法上的注解,打印日志
@Retention(RetentionPolicy.RUNTIME)//反射式可拿到值
public @interface LogAnnotation {

    String value();

}
