package com.streamingsearcher.services;

import com.streamingsearcher.models.Example;
import com.streamingsearcher.models.MultimediaPlatforms.Content;
import com.streamingsearcher.models.MultimediaPlatforms.D;
import com.streamingsearcher.models.Title;
import org.springframework.http.ResponseEntity;


public interface TitleService {
    D findTitles(String name);
    Content getMultimediaById(String id);
}
