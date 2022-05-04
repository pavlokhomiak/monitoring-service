package com.project.monitoringservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class MonitoredEndpointResponse {

    private String name;

    private String url;

    private LocalDateTime dateOfCreation;

    private LocalDateTime dateOfLastCheck;

    private Integer monitoredInterval;
}
