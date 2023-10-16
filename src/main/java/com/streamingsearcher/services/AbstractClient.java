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
        headers.set("X-Rapidapi-Key", "9000e51be2msh129e85ca6efb0d6p19acbajsn2cc83cf7161a");
        return headers;
    }
}
