package com.project.monitoringservice.controller;

import com.project.monitoringservice.dto.MonitoredEndpointRequest;
import com.project.monitoringservice.dto.MonitoredEndpointResponse;
import com.project.monitoringservice.facade.MonitoredEndpointFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j(topic = "http-logger")
@RequestMapping("/monitored-endpoint")
public class MonitoredEndpointController {

    private final MonitoredEndpointFacade facade;

    @PostMapping("/create")
    public ResponseEntity<MonitoredEndpointResponse> create(@RequestBody MonitoredEndpointRequest request) {
        return new ResponseEntity<>(facade.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonitoredEndpointResponse> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(facade.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<MonitoredEndpointResponse> update(@PathVariable Integer id, @RequestBody MonitoredEndpointRequest request) {
        return new ResponseEntity<>(facade.update(id, request), HttpStatus.OK);
    }

    @PutMapping("/{id}/delete")
    public void delete(@PathVariable Integer id) {
        facade.delete(id);
    }

}
