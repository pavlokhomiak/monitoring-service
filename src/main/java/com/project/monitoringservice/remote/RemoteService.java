package com.project.monitoringservice.remote;

import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.net.URI;

@Service
@RequiredArgsConstructor
public class RemoteService {

    private final RemoteClient client;

    public Response ping(String url) {
        return client.ping(URI.create(url));
    }
}
