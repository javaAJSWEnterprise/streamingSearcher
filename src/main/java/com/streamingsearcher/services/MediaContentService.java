package com.streamingsearcher.services;

import com.streamingsearcher.models.MediaContent;

import java.util.List;

public interface MediaContentService {
    MediaContent createMediaContent(MediaContent mediaContent);
    MediaContent updateMediaContent(MediaContent mediaContent);
    void deleteMediaContent(String mediaContentId);
    MediaContent getMediaContentById(String mediaContentId);
    List<MediaContent> getAllMediaContent();
    MediaContent getMediaContentWithPlatforms(String mediaContentId);
}
