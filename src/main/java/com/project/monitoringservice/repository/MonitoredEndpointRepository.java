package com.project.monitoringservice.repository;

import com.project.monitoringservice.model.MonitoredEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoredEndpointRepository extends JpaRepository<MonitoredEndpoint, Integer> {
}
