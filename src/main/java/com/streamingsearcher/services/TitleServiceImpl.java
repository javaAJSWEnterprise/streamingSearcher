package com.streamingsearcher.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.streamingsearcher.models.Example;
import com.streamingsearcher.models.MultimediaPlatforms.Content;
import com.streamingsearcher.models.Title;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

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
    public Content getMultimediaById(String id){
        String json = "{ \"result\": {\"type\": \"movie\",\"title\": \"Robots\",\"streamingInfo\": {\"ar\": [ {\"service\": \"disney\",\"streamingType\": \"subscription\",\"quality\": \"hd\",\"link\": \"https://www.disneyplus.com/movies/robots/1WZb0cTgTKlY\",\"videoLink\": \"https://www.disneyplus.com/video/a9b45364-cb30-467b-8a43-382a934c4953\",\"availableSince\": 1649652397 }, {\"service\": \"apple\",\"streamingType\": \"addon\",\"quality\": \"hd\",\"addon\": \"tvs.sbd.1000216\",\"link\": \"https://tv.apple.com/ar/movie/robots/umc.cmc.4l9smhgi3t0kl8aiuhrvwz5e7\",\"availableSince\": 1694782217 } ] },\"year\": 2005,\"imdbId\": \"tt0358082\",\"tmdbId\": 9928 }}";

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Content content = objectMapper.readValue(json, Content.class);


            if ( content.getResult().getStreamingInfo() != null) {
                // Acceder a "streamingInfo" si no está vacío
                Map<String, List<Map<String, Object>>> streamingInfo = content.getResult().getStreamingInfo();
                // Puedes acceder a las propiedades dentro de "streamingInfo" según sea necesario
            }

            return content;
            // Puedes acceder a otras propiedades dentro de "result" si es necesario
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseEntity<?> prueba() {
        HttpEntity<?> entity = new HttpEntity<>(buildHeaders());
        ResponseEntity<String> response = restTemplate.exchange("asd", HttpMethod.GET, entity, String.class);
        return response;
    }
}
