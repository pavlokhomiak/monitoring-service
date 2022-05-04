package com.project.monitoringservice.mapper;

import com.project.monitoringservice.dto.MonitoringResultResponse;
import com.project.monitoringservice.model.MonitoringResult;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MonitoringResultMapper extends BaseMapper<MonitoringResult> {

    private final ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        modelMapper.typeMap(MonitoringResult.class, MonitoringResultResponse.class)
            .addMapping(src -> src.getMonitoredEndpoint().getId(), MonitoringResultResponse::setMonitoredEndpointId);
    }

    @Override
    public MonitoringResult updateMap(MonitoringResult source, MonitoringResult target) {
        return null;
    }
}
