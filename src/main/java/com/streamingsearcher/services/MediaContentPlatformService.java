package com.streamingsearcher.services;

import com.streamingsearcher.models.MediaContent;
import com.streamingsearcher.models.MediaContentPlatform;

import java.util.List;

public interface MediaContentPlatformService {

    MediaContentPlatform createMediaContentPlatform(MediaContentPlatform mediaContentPlatform);

    List<MediaContentPlatform> getMediaContentPlatformsByContentId(String id);
}
