package com.crazym8nd.personclientservice.service;


import com.crazym8nd.commonsdto.dto.response.IndividualInfoResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
public class PersonClient {
    private final WebClient webClient;

    private final UUID id = UUID.fromString("141744ab-e76a-4dcc-8cc5-cc518627b3f0");

    public PersonClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public IndividualInfoResponse getIndividualWithId() {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(IndividualInfoResponse.class)
                .block();
    }

}
