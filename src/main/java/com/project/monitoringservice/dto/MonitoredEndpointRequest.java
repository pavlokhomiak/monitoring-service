package com.project.monitoringservice.dto;

import lombok.Data;

@Data
public class MonitoredEndpointRequest {

    private String name;

    private String url;

    private Integer monitoredInterval;

    private Integer ownerId;
}
