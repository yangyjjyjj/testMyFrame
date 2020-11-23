package com.yjj.util.schedule;

import com.yjj.util.annotation.ScheduleAnnotation;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

@ScheduleAnnotation   // 2.开启定时任务
@Setter
public abstract class ScheduleTask implements SchedulingConfigurer {

    protected static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    protected  String timeRule = "";

    public abstract void init();

    public  abstract void tempSchedule();

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        init();
        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> tempSchedule(),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cron = timeRule;
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code ..
                        logger.error("{}未指定定时任务周期",this.getClass().getName());
                        return null;
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }

}
