package com.streamingsearcher.services;

import com.streamingsearcher.models.Platform;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface PlatformService {
    Platform createPlatform(Platform platform);

    Set<Platform> findPlatformsByServiceValues(Set<String> serviceValues);
}
