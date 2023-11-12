package com.streamingsearcher.services;

import com.streamingsearcher.entities.Favorites;
import com.streamingsearcher.models.MediaContent;
import com.streamingsearcher.repositories.FavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteServiceImpl implements FavoriteService{

    private FavoriteRepository repository;

    public FavoriteServiceImpl (FavoriteRepository repository){
        this.repository = repository;
    }

    @Override
    public void createFavorites(MediaContent md, String userId) {
         Favorites favorites = new Favorites(md,userId);
         String mediaContentId = md.getId();
         Optional<Favorites> favoritesFind= repository.findByMediaContentIdAndUserId(mediaContentId, userId);

         if(!favoritesFind.isEmpty()){
             repository.delete(favoritesFind.get());
         }else {
             repository.save(favorites);
         }
    }

    @Override
    public List<Favorites> getFavoritesByUserId(String id) {
        return  repository.findByMediaContentId(id);
    }
}
