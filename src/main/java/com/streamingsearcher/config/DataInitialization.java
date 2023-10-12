package com.streamingsearcher.config;


import com.streamingsearcher.models.Platform;
import com.streamingsearcher.repositories.PlatformRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class DataInitialization implements CommandLineRunner {

    private final PlatformRepository platformRepository;

    public DataInitialization(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    @Override
    public void run(String... args) {
        if (platformRepository.count() == 0) {
            List<Platform> platforms = new LinkedList<>();
            Platform netflix = new Platform("netflix","Netflix");
            Platform amazonPrimeVideo = new Platform("prime","Amazon Prime Video");
            Platform disneyPlus = new Platform("disney","Disney+");
            Platform hboMax = new Platform("hbo","HBO Max");
            Platform hulu = new Platform("hulu","Hulu Tv");
            Platform paramountPlus = new Platform("paramount","Paramount+");
            Platform appleTV = new Platform("apple","Apple TV");

            platformRepository.saveAll(List.of(netflix, amazonPrimeVideo,disneyPlus, hboMax, hulu, paramountPlus, appleTV));
        }
    }
}