package com.crazym8nd.personclientservice.service;


import com.crazym8nd.commonsdto.dto.IndividualRegistrationDto;
import com.crazym8nd.commonsdto.dto.UpdateRequestIndividualDto;
import com.crazym8nd.commonsdto.dto.response.IndividualInfoResponse;
import com.crazym8nd.commonsdto.dto.response.RegistrationResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class PersonClient {
    private final WebClient webClient;

    public PersonClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public IndividualInfoResponse getIndividualWithId(UUID individualId) {
        return webClient.get()
                .uri("/{id}", individualId)
                .retrieve()
                .bodyToMono(IndividualInfoResponse.class)
                .block();
    }

    public RegistrationResponse createIndividual(IndividualRegistrationDto individualDto) {
        return webClient.post()
                .body(Mono.just(individualDto), IndividualRegistrationDto.class)
                .retrieve()
                .bodyToMono(RegistrationResponse.class)
                .block();
    }

    public void deleteIndividual(UUID individualId) {
        webClient.delete()
                .uri("/{id}", individualId)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public UpdateRequestIndividualDto updateIndividual(UpdateRequestIndividualDto updateRequestIndividualDto) {
        return webClient.put()
                .uri("/{id}", updateRequestIndividualDto.getIndividualDto().getId())
                .body(Mono.just(updateRequestIndividualDto), UpdateRequestIndividualDto.class)
                .retrieve()
                .bodyToMono(UpdateRequestIndividualDto.class)
                .block();
    }

}
