package com.project.monitoringservice.mapper;

import com.project.monitoringservice.dto.TimerInfo;
import com.project.monitoringservice.model.BaseEntity;
import com.project.monitoringservice.model.MonitoredEndpoint;
import com.project.monitoringservice.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class MonitoredEndpointMapper extends BaseMapper<MonitoredEndpoint> {

    private final ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        modelMapper.typeMap(MonitoredEndpoint.class, TimerInfo.class)
            .addMapping(BaseEntity::getId, TimerInfo::setMonitoredEndpointId);
    }

    @Override
    public MonitoredEndpoint updateMap(MonitoredEndpoint source, MonitoredEndpoint target) {
        String url = source.getUrl() == null ? target.getUrl() : source.getUrl();
        String name = source.getName() == null ? target.getName() : source.getName();
        Integer monitoredInterval = source.getMonitoredInterval() == null ? target.getMonitoredInterval() : source.getMonitoredInterval();
        LocalDateTime dateOfCreation = source.getDateOfCreation() == null ? target.getDateOfCreation() : source.getDateOfCreation();
        LocalDateTime dateOfLastCheck = source.getDateOfLastCheck() == null ? target.getDateOfLastCheck() : source.getDateOfLastCheck();
        User owner = source.getOwner() == null ? target.getOwner() : source.getOwner();
        return target
            .setUrl(url)
            .setName(name)
            .setMonitoredInterval(monitoredInterval)
            .setDateOfCreation(dateOfCreation)
            .setDateOfLastCheck(dateOfLastCheck)
            .setOwner(owner);
    }
}
