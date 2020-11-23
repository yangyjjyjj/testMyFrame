package com.yjj.util.schedule;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
public class TestTask extends  ScheduleTask {


    public void init(){
        this.setTimeRule("0/5 * * * * ?");
    }

    @Override
    public void tempSchedule() {
        logger.info("{}正在执行动态定时任务: {}",TestTask.class.getName(), LocalDateTime.now().toLocalTime());
    }
}
