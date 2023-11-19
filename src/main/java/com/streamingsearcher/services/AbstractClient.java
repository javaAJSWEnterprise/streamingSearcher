package com.streamingsearcher.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractClient {

    @Value("${find-title-by-name}")
    protected String baseUrl;

    protected final RestTemplate restTemplate;


    public AbstractClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    protected HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Rapidapi-Key", "ea7d5eb311msh8e2903dcba31779p16bbaajsn2ec0072f2ad9");
        return headers;
    }
}
