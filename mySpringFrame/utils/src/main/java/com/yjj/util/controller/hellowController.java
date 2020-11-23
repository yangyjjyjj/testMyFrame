package com.yjj.util.controller;

import com.yjj.util.annotation.LogAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/hellow")
public class hellowController {

    @LogAnnotation("测试日志注解")
    @RequestMapping("/LogAop")
    public Map<String, Object> getLogAop(){
        return null;
    }

}
