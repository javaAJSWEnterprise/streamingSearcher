package com.streamingsearcher.services;

import com.streamingsearcher.models.Platform;
import com.streamingsearcher.repositories.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatformServiceImpl implements PlatformService {

    private final PlatformRepository platformRepository;

    @Autowired
    public PlatformServiceImpl(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    @Override
    public Platform createPlatform(Platform platform) {
        return platformRepository.save(platform);
    }
}