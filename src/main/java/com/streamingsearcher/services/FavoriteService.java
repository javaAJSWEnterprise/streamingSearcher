package com.streamingsearcher.services;

import com.streamingsearcher.entities.Favorites;
import com.streamingsearcher.models.MediaContent;


import java.util.List;

public interface FavoriteService {
    void createFavorites(MediaContent md, String userId);

    List<Favorites> getFavoritesByUserId(String id);
}
