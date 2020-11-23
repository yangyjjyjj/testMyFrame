package com.yjj.util;

import com.yjj.util.aspect.LogAspect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogAspectTest {
    @Autowired
    private LogAspect logAspect;

    @Test
    public void testLogAnnotation(){
        logAspect.testLogAnnotation();
    }

}
