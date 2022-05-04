package com.project.monitoringservice.service;

import com.project.monitoringservice.model.MonitoringResult;
import java.util.List;

public interface MonitoringResultService extends BaseService<MonitoringResult> {

    List<MonitoringResult> findLastTenByMonitoredEndpointId(Integer id);
}
