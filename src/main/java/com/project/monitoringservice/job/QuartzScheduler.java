package com.project.monitoringservice.job;

import com.project.monitoringservice.dto.TimerInfo;
import com.project.monitoringservice.model.MonitoredEndpoint;
import com.project.monitoringservice.service.MonitoredEndpointService;
import com.project.monitoringservice.utils.TimerUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@RequiredArgsConstructor
public class QuartzScheduler {

    private final Scheduler scheduler;
    private final ModelMapper modelMapper;
    private final MonitoredEndpointService monitoredEndpointService;

    @PostConstruct
    public void init() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            throw new RuntimeException("Error occurred while start scheduler", e);
        }
        monitoredEndpointService.findAll().forEach(this::scheduleJob);
    }

    public void scheduleJob(MonitoredEndpoint endpoint) {
        scheduleJob(PingJob.class, endpoint);
    }

    @SneakyThrows
    public void scheduleJob(Class<? extends Job> jobClass, MonitoredEndpoint endpoint) {
        TimerInfo info = modelMapper.map(endpoint, TimerInfo.class);
        JobDetail jobDetail = TimerUtils.buildJobDetail(jobClass, info);
        Trigger trigger = TimerUtils.buildTrigger(info);
        scheduler.scheduleJob(jobDetail, trigger);
    }

    public void rescheduleJob(MonitoredEndpoint endpoint) {
        rescheduleJob(PingJob.class, endpoint);
    }

    public void rescheduleJob(Class<? extends Job> jobClass, MonitoredEndpoint endpoint) {
        TimerInfo info = modelMapper.map(endpoint, TimerInfo.class);
        JobDetail jobDetail = TimerUtils.buildJobDetail(jobClass, info);
        Trigger trigger = TimerUtils.buildTrigger(info);
        JobKey jobKey = JobKey.jobKey(String.valueOf(info.getMonitoredEndpointId()));
        try {
            scheduler.deleteJob(jobKey);
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException("Error occurred while reschedule", e);
        }
    }

    public void deleteJob(MonitoredEndpoint endpoint) {
        JobKey jobKey = JobKey.jobKey(String.valueOf(endpoint.getId()));
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            throw new RuntimeException("Error occurred while delete job", e);
        }
    }

    @PreDestroy
    public void preDestroy() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            throw new RuntimeException("Error occurred while shutdown scheduler", e);
        }
    }
}
