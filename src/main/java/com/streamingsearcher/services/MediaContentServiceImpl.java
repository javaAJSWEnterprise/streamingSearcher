package com.streamingsearcher.services;

import com.streamingsearcher.models.MediaContent;
import com.streamingsearcher.repositories.MediaContentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MediaContentServiceImpl implements MediaContentService {

    private final MediaContentRepository mediaContentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MediaContentServiceImpl(MediaContentRepository mediaContentRepository, ModelMapper modelMapper) {
        this.mediaContentRepository = mediaContentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MediaContent createMediaContent(MediaContent mediaContent) {
        return mediaContentRepository.save(mediaContent);
    }

    @Override
    public MediaContent updateMediaContent(MediaContent mediaContent) {
        return mediaContentRepository.save(mediaContent);
    }

    @Override
    public void deleteMediaContent(String mediaContentId) {
        mediaContentRepository.deleteById(mediaContentId);
    }

    @Override
    public MediaContent getMediaContentById(String mediaContentId) {
        return mediaContentRepository.findMediaContentByIdWithPlatformLoad(mediaContentId);
    }

    @Override
    public List<MediaContent> getAllMediaContent() {
        return mediaContentRepository.findAll();
    }

    @Override
    public MediaContent getMediaContentWithPlatforms(String mediaContentId) {
        return mediaContentRepository.findMediaContentWithPlatforms(mediaContentId);
    }
}