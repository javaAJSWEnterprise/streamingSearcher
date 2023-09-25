package com.streamingsearcher.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.streamingsearcher.models.Example;
import com.streamingsearcher.models.Title;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TitleServiceImpl extends AbstractClient implements TitleService{

    public TitleServiceImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }

    //String uri = baseUrl;
    @Value("${get-info-title}")
    String getInfoTitle;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ResponseEntity<Title> findTitles(String name) {
        HttpEntity<Title> entity = new HttpEntity<>(buildHeaders());
        String uri = baseUrl + name;
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        Title title = null;
        try {
            title = objectMapper.readValue(response.getBody().toString(), Title.class);
        } catch (Exception e) {

        }
        System.out.println(title);

        return null;
    }

    @Override
    public Example getInfoTitle(String id) {
        HttpEntity<Title> entity = new HttpEntity<>(buildHeaders());
        String uri = getInfoTitle + id;
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        Example result = null;
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            result = objectMapper.readValue(response.getBody(), Example.class);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(result);
        return result;
    }

    @Override
    public ResponseEntity<?> prueba() {
        HttpEntity<?> entity = new HttpEntity<>(buildHeaders());
        ResponseEntity<String> response = restTemplate.exchange("asd", HttpMethod.GET, entity, String.class);
        return response;
    }
}
