package com.streamingsearcher.services;

import com.streamingsearcher.models.Example;
import com.streamingsearcher.models.MultimediaPlatforms.Content;
import com.streamingsearcher.models.Title;
import org.springframework.http.ResponseEntity;


public interface TitleService {
    ResponseEntity<Title> findTitles(String name);
    Example getInfoTitle(String name);
    ResponseEntity<?> prueba();
    Content getMultimediaById(String id);
}
