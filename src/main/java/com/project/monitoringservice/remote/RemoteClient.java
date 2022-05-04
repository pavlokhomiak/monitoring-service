package com.project.monitoringservice.remote;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.net.URI;

@FeignClient(name = "remote-client", url = "dummy")
public interface RemoteClient {

    @GetMapping
    Response ping(URI baseUrl);
}
