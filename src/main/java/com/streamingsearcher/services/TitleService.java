package com.streamingsearcher.services;

import com.streamingsearcher.models.MultimediaPlatforms.Content;
import com.streamingsearcher.models.MultimediaPlatforms.D;

public interface TitleService {
    D findTitles(String name);
    Content getMultimediaById(String id);
}
