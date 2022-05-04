package com.project.monitoringservice.facade;

import com.project.monitoringservice.dto.MonitoringResultResponse;
import com.project.monitoringservice.service.MonitoringResultService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MonitoringResultFacade {

    private final ModelMapper modelMapper;
    private final MonitoringResultService service;

    public List<MonitoringResultResponse> getByMonitoredEndpointId(Integer id) {
        return service.findLastTenByMonitoredEndpointId(id).stream()
            .map(result -> modelMapper.map(result, MonitoringResultResponse.class))
            .collect(Collectors.toList());
    }
}
