package com.streamingsearcher.services;

import com.streamingsearcher.models.MediaContentPlatform;
import com.streamingsearcher.repositories.MediaContentPlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaContentPlatformServiceImpl implements MediaContentPlatformService{


    private final MediaContentPlatformRepository mediaContentPlatformRepository;

    @Autowired
    public MediaContentPlatformServiceImpl(MediaContentPlatformRepository mediaContentPlatformRepository) {
        this.mediaContentPlatformRepository = mediaContentPlatformRepository;
    }
    @Override
    public MediaContentPlatform createMediaContentPlatform(MediaContentPlatform mediaContentPlatform) {
        return mediaContentPlatformRepository.save(mediaContentPlatform);
    }

    @Override
    public List<MediaContentPlatform> getMediaContentPlatformsByContentId(String id) {
        return mediaContentPlatformRepository.findByMediaContentIdWithPlatforms(id);
    }

}
