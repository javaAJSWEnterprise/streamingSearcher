package com.streamingsearcher.services;

import com.streamingsearcher.models.MediaContent;
import com.streamingsearcher.models.MultimediaPlatforms.MultimediaImage;
import com.streamingsearcher.models.MultimediaPlatforms.ResultMultimedia;
import com.streamingsearcher.repositories.MediaContentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<MediaContent> find = mediaContentRepository.findById(mediaContent.getId());
        if(find.isEmpty()){
            return mediaContentRepository.save(mediaContent);
        }
        return null;
    }

    @Override
    public MediaContent updateMediaContent(MediaContent mediaContent) {
        return mediaContentRepository.save(mediaContent);
    }


    @Override
    public MediaContent getMediaContentById(String mediaContentId) {

        Optional<MediaContent> mediaContent = mediaContentRepository.findById(mediaContentId);

        if(!mediaContent.isEmpty()){
            return mediaContent.get();
        }

       return null;
    }
}