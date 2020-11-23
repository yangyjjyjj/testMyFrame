package com.yjj.util.annotation;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.TYPE})//用于方法上的注解,打印日志
@Retention(RetentionPolicy.RUNTIME)//反射式可拿到值
@EnableScheduling
public @interface ScheduleAnnotation {

}
