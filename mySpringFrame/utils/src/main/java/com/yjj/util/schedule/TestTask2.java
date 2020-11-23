package com.yjj.util.schedule;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
public class TestTask2 extends  ScheduleTask {


    public void init(){
        this.setTimeRule("0/7 * * * * ?");
    }

    @Override
    public void tempSchedule() {
        logger.info("{}正在执行动态定时任务: {}", TestTask2.class.getName(), LocalDateTime.now().toLocalTime());
    }
}
