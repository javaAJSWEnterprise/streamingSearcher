package com.streamingsearcher.repositories;

import com.streamingsearcher.models.MediaContentPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MediaContentPlatformRepository extends JpaRepository<MediaContentPlatform, Long> {

    @Query("SELECT mcp FROM MediaContentPlatform mcp " +
            "LEFT JOIN FETCH mcp.platform " +
            "WHERE mcp.mediaContent.id = :mediaContentId")
    List<MediaContentPlatform> findByMediaContentIdWithPlatforms(@Param("mediaContentId") String mediaContentId);
}
