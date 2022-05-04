package com.project.monitoringservice.service.impl;

import com.project.monitoringservice.mapper.MonitoringResultMapper;
import com.project.monitoringservice.model.MonitoringResult;
import com.project.monitoringservice.repository.MonitoringResultRepository;
import com.project.monitoringservice.service.MonitoringResultService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MonitoringResultServiceImpl extends BaseServiceImpl<MonitoringResult, MonitoringResultRepository, MonitoringResultMapper>
    implements MonitoringResultService {

    private final MonitoringResultRepository repository;

    public MonitoringResultServiceImpl(MonitoringResultRepository repository, MonitoringResultMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MonitoringResult> findLastTenByMonitoredEndpointId(Integer id) {
        return repository.findLastTenByMonitoredEndpointId(id);
    }
}
