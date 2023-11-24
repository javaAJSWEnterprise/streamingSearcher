package com.streamingsearcher.services;

import com.streamingsearcher.models.MultimediaPlatforms.Content;
import com.streamingsearcher.models.MultimediaPlatforms.D;

public interface ApiService {
    D findTitles(String name);
    Content getMultimediaById(String id);
}
