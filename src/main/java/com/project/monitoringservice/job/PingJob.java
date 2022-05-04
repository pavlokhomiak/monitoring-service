package com.project.monitoringservice.job;

import com.project.monitoringservice.config.ApplicationContextProvider;
import com.project.monitoringservice.dto.TimerInfo;
import com.project.monitoringservice.service.impl.PingService;
import lombok.NoArgsConstructor;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class PingJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        TimerInfo info = (TimerInfo) jobDataMap.get(PingJob.class.getSimpleName());
        PingService service = ApplicationContextProvider.getApplicationContext().getBean(PingService.class);
        service.ping(info);
    }
}
