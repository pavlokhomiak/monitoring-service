package com.project.monitoringservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TimerInfo {

    private int monitoredEndpointId;
    private int monitoredInterval;
    private String url;
}
