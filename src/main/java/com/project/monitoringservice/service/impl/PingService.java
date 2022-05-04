package com.project.monitoringservice.service.impl;

import com.project.monitoringservice.dto.TimerInfo;
import com.project.monitoringservice.model.MonitoredEndpoint;
import com.project.monitoringservice.model.MonitoringResult;
import com.project.monitoringservice.remote.RemoteService;
import com.project.monitoringservice.service.MonitoredEndpointService;
import com.project.monitoringservice.service.MonitoringResultService;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j(topic = "http-logger")
public class PingService {

    private final RemoteService remoteService;
    private final MonitoringResultService monitoringResultService;
    private final MonitoredEndpointService monitoredEndpointService;

    public void ping(TimerInfo info) {
        int id = info.getMonitoredEndpointId();
        MonitoredEndpoint monitoredEndpoint = monitoredEndpointService.findById(id).orElseThrow(RuntimeException::new);
        String url = info.getUrl();
        Response response = remoteService.ping(url);
        MonitoringResult monitoringResult = getMonitoringResult(monitoredEndpoint, response);
        monitoringResultService.save(monitoringResult);
    }

    private MonitoringResult getMonitoringResult(MonitoredEndpoint monitoredEndpoint, Response response) {
        byte[] bytes;
        try {
            bytes = StreamUtils.copyToByteArray(response.body().asInputStream());
        } catch (IOException e) {
            log.info("Failed to process response body.", e);
            throw new RuntimeException("Failed to process response body.", e);
        }
        int status = response.status();
        String payload = new String(bytes);
        log.trace("Status: {}, payload: {}", status, payload);
        return new MonitoringResult()
            .setDateOfCheck(LocalDateTime.now())
            .setMonitoredEndpoint(monitoredEndpoint)
            .setReturnedHttpStatusCode(status)
            .setReturnedPayload(payload);
    }
}
