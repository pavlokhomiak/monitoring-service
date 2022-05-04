package com.project.monitoringservice.service.impl;

import com.project.monitoringservice.mapper.MonitoredEndpointMapper;
import com.project.monitoringservice.model.MonitoredEndpoint;
import com.project.monitoringservice.repository.MonitoredEndpointRepository;
import com.project.monitoringservice.service.MonitoredEndpointService;
import org.springframework.stereotype.Service;

@Service
public class MonitoredEndpointServiceImpl extends BaseServiceImpl<MonitoredEndpoint, MonitoredEndpointRepository, MonitoredEndpointMapper>
    implements MonitoredEndpointService {

    public MonitoredEndpointServiceImpl(MonitoredEndpointRepository repository, MonitoredEndpointMapper mapper) {
        super(repository, mapper);
    }
}
