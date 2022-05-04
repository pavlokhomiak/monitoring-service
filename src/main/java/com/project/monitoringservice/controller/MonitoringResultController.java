package com.project.monitoringservice.controller;

import com.project.monitoringservice.dto.MonitoringResultResponse;
import com.project.monitoringservice.facade.MonitoringResultFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j(topic = "http-logger")
@RequestMapping("/monitoring-result")
public class MonitoringResultController {

    private final MonitoringResultFacade facade;

    @GetMapping(value = "/{monitoredEndpointId}")
    public ResponseEntity<List<MonitoringResultResponse>> getByMonitoredEndpointId(@PathVariable Integer monitoredEndpointId) {
        List<MonitoringResultResponse> resultList = facade.getByMonitoredEndpointId(monitoredEndpointId);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
}
