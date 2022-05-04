package com.project.monitoringservice.utils;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import com.project.monitoringservice.dto.TimerInfo;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Component;

@Component
public class TimerUtils {

    public static JobDetail buildJobDetail(Class<? extends Job> jobClass, TimerInfo info) {
        JobKey jobKey = JobKey.jobKey(String.valueOf(info.getMonitoredEndpointId()));
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(jobClass.getSimpleName(), info);
        return JobBuilder
            .newJob(jobClass)
            .usingJobData(jobDataMap)
            .withIdentity(jobKey)
            .build();
    }

    public static Trigger buildTrigger(TimerInfo info) {
        TriggerKey triggerKey = TriggerKey.triggerKey(String.valueOf(info.getMonitoredEndpointId()));
        JobKey jobKey = JobKey.jobKey(String.valueOf(info.getMonitoredEndpointId()));
        return TriggerBuilder.newTrigger()
            .forJob(jobKey)
            .withIdentity(triggerKey)
            .withSchedule(simpleSchedule().repeatForever().withIntervalInSeconds(info.getMonitoredInterval()))
            .build();
    }
}
