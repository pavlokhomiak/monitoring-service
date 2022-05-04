package com.project.monitoringservice.facade;

import com.project.monitoringservice.dto.MonitoredEndpointRequest;
import com.project.monitoringservice.dto.MonitoredEndpointResponse;
import com.project.monitoringservice.job.QuartzScheduler;
import com.project.monitoringservice.model.MonitoredEndpoint;
import com.project.monitoringservice.model.User;
import com.project.monitoringservice.service.MonitoredEndpointService;
import com.project.monitoringservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class MonitoredEndpointFacade {

    private final ModelMapper modelMapper;
    private final MonitoredEndpointService service;
    private final QuartzScheduler quartzScheduler;
    private final UserService userService;

    public MonitoredEndpointResponse create(MonitoredEndpointRequest request) {
        User owner = userService.findById(request.getOwnerId()).orElseThrow(RuntimeException::new);
        MonitoredEndpoint monitoredEndpoint = modelMapper.map(request, MonitoredEndpoint.class);
        monitoredEndpoint.setOwner(owner)
            .setDateOfCreation(LocalDateTime.now())
            .setDateOfLastCheck(LocalDateTime.now());
        MonitoredEndpoint savedEndpoint = service.save(monitoredEndpoint);
        quartzScheduler.scheduleJob(savedEndpoint);
        return modelMapper.map(savedEndpoint, MonitoredEndpointResponse.class);
    }

    public MonitoredEndpointResponse findById(Integer id) {
        return service.findById(id)
            .map(endpoint -> modelMapper.map(endpoint, MonitoredEndpointResponse.class))
            .orElseThrow(RuntimeException::new);
    }

    public MonitoredEndpointResponse update(Integer id, MonitoredEndpointRequest request) {
        MonitoredEndpoint endpoint = modelMapper.map(request, MonitoredEndpoint.class);
        MonitoredEndpoint updatedEndpoint = service.update(id, endpoint).orElseThrow(RuntimeException::new);
        quartzScheduler.rescheduleJob(updatedEndpoint);
        return modelMapper.map(updatedEndpoint, MonitoredEndpointResponse.class);
    }

    public void delete(Integer id) {
        MonitoredEndpoint endpoint = service.findById(id).orElseThrow(RuntimeException::new);
        quartzScheduler.deleteJob(endpoint);
        service.deleteById(id);
    }
}
