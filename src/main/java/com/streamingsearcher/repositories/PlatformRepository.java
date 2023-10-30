package com.streamingsearcher.repositories;

import com.streamingsearcher.models.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {

    @Query("SELECT DISTINCT p FROM Platform p " +
            "WHERE p.apiId IN :serviceValues")
    Set<Platform> findPlatformsByServiceValues(@Param("serviceValues") Set<String> serviceValues);


}
