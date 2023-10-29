package com.streamingsearcher.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.streamingsearcher.models.MultimediaPlatforms.Content;
import com.streamingsearcher.models.MultimediaPlatforms.D;
import com.streamingsearcher.models.MultimediaPlatforms.ResultMultimedia;
import com.streamingsearcher.models.MultimediaPlatforms.StreamingInfo;
import com.streamingsearcher.models.Title;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class TitleServiceImpl extends AbstractClient implements TitleService{

    public TitleServiceImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }

    //String uri = baseUrl;
    @Value("${get-info-title}")
    String getInfoTitle;

    ObjectMapper objectMapper = new ObjectMapper();

    public D findTitles(String name) {
        HttpEntity<D> entity = new HttpEntity<>(buildHeaders());
        String uri = baseUrl + name;
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        D title = null;
        try {
            title = objectMapper.readValue(response.getBody(), D.class);

            return title;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Content getMultimediaById(String id){

        HttpEntity<Title> entity = new HttpEntity<>(buildHeaders());
        String uri = getInfoTitle + id;
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        try {
            Content content = objectMapper.readValue(response.getBody(), Content.class);
            ResultMultimedia resultMultimedia = content.getResult();
            if (resultMultimedia != null && resultMultimedia.getStreamingInfo() != null) {
                Map<String, Set<StreamingInfo>> streamingInfo = resultMultimedia.getStreamingInfo();
                Set<StreamingInfo> arServices = streamingInfo.get("ar");
                Map<String, Set<StreamingInfo>> services = new HashMap<String, Set<StreamingInfo>>();
                services.put("ar", arServices);
                resultMultimedia.setStreamingInfo(services);
            }
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
