package com.streamingsearcher.repositories;

import com.streamingsearcher.entities.Favorites;
import com.streamingsearcher.models.MediaContentPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorites, Long> {

    @Query("SELECT f FROM Favorites f " +
            "LEFT JOIN FETCH f.mediaContent mc " +
            "WHERE f.userId = :userId")
    List<Favorites> findByMediaContentId(@Param("userId") String userId);

    @Query("SELECT f FROM Favorites f " +
            "WHERE f.mediaContent.id = :mediaContentId AND f.userId = :userId")
    Optional<Favorites> findByMediaContentIdAndUserId(
            @Param("mediaContentId") String mediaContentId,
            @Param("userId") String userId
    );
}
