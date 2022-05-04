package com.project.monitoringservice.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MonitoringResultResponse {

    private Integer id;
    private LocalDateTime dateOfCheck;
    private Integer returnedHttpStatusCode;
    private String returnedPayload;
    private Long monitoredEndpointId;
}
