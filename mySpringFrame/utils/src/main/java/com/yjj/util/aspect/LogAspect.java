package com.yjj.util.aspect;

import com.yjj.util.annotation.LogAnnotation;
import com.yjj.util.property.OperationLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 日志操作切面类
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);


    @Pointcut("@annotation(com.yjj.util.annotation.LogAnnotation)")
    public void doAspect(){}

    @Before("doAspect()")
    public void doBefore(){
        logger.info("=======开始保存操作日志");
    }

    @AfterReturning("doAspect()")
    public void doAfter(JoinPoint joinPoint){
        getOpreationLog(joinPoint);
    }

    public OperationLogger getOpreationLog(JoinPoint joinPoint){
        OperationLogger operationLogger = new OperationLogger();
        operationLogger.setOperation_ip("192.168.0.120");
        operationLogger.setOperation_time("2019-12-03 19:43:32");
        operationLogger.setSysUser_name("杨家进");
        LogAnnotation logAnnotation = null;
        try {
            logAnnotation = getLogAnnotation(joinPoint);
        } catch (ClassNotFoundException e) {
            logger.error("===异常通知===");
            logger.error("异常信息:{}", e.getMessage());
        }
        operationLogger.setOperation_record(logAnnotation.value());
        //logger.error("测试注解");
        System.out.print("测试注解"+operationLogger);
        return operationLogger;
    }

    /**
     * 获取对应方法上的LogAnnotation
     * @param joinPoint
     * @return
     */
    public static LogAnnotation getLogAnnotation(JoinPoint joinPoint) throws ClassNotFoundException {
        LogAnnotation logAnnotation = null;//日志注解类
        //获取切点类名
        String targetName = joinPoint.getTarget().getClass().getName();
        //获取切点方法名
        String methodName = joinPoint.getSignature().getName();
        //获取切点方法参数
        Object[] arguments = joinPoint.getArgs();
        //获取目标类
        Class targetClass = Class.forName(targetName);
        //获取目标类方法
        Method[] methods = targetClass.getMethods();
        //获取目标类上方法的日志注解
        for(Method method : methods){
            if(method.getName().equals(methodName)){
                //方法名相同时判断参数个数
                Class[] clazzs =  method.getParameterTypes();
                if(arguments.length == clazzs.length){
                    logAnnotation = method.getAnnotation(LogAnnotation.class);
                    break;
                }
            }
        }
        return logAnnotation;
    }

    @LogAnnotation("测试日志切面注解")
    public void testLogAnnotation(){
        logger.info("========测试日志切面注解");
    }

}
