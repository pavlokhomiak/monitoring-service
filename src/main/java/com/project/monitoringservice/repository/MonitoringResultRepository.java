package com.project.monitoringservice.repository;

import com.project.monitoringservice.model.MonitoringResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MonitoringResultRepository extends JpaRepository<MonitoringResult, Integer> {

    @Query(nativeQuery = true, value =
        "select * from monitoring_result r where monitored_endpoint_id = :id order by r.date_of_check desc limit 10;")
    List<MonitoringResult> findLastTenByMonitoredEndpointId(@Param("id") Integer id);
}
