package com.streamingsearcher.repositories;

import com.streamingsearcher.models.MediaContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaContentRepository extends JpaRepository<MediaContent, String> {
    @Query("SELECT p FROM Platform p JOIN p.mediaContents mc WHERE mc.id = :mediaContentId")
    MediaContent findMediaContentWithPlatforms(@Param("mediaContentId") String mediaContentId);

    @Query("SELECT mc FROM MediaContent mc WHERE mc.id = :mediaContentId AND mc.platformload = true")
    MediaContent findMediaContentByIdWithPlatformLoad(@Param("mediaContentId") String mediaContentId);
}
