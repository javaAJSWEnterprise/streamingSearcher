package com.streamingsearcher.services;

import com.streamingsearcher.models.MediaContent;
import com.streamingsearcher.models.MultimediaPlatforms.ResultMultimedia;

import java.util.List;

public interface MediaContentService {
    MediaContent createMediaContent(MediaContent mediaContent);
    MediaContent updateMediaContent(MediaContent mediaContent);
    MediaContent getMediaContentById(String mediaContentId);
}
