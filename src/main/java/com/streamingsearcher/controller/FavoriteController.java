package com.streamingsearcher.controller;

import com.streamingsearcher.entities.Favorites;
import com.streamingsearcher.models.MediaContent;
import com.streamingsearcher.models.MultimediaPlatforms.MultimediaImage;
import com.streamingsearcher.models.MultimediaPlatforms.ResultMultimedia;
import com.streamingsearcher.security.JwtServices;
import com.streamingsearcher.services.*;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/v1/favorite")
public class FavoriteController {

    @Autowired
    private MediaContentServiceImpl mediaContentService;

    @Autowired
    private JwtServices jwtServices;

    @Autowired
    private FavoriteServiceImpl favoriteService;

    @PostMapping("/{movieId}")
    public ResponseEntity<?> createFavorite(@PathVariable String movieId, @RequestHeader(name = "Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7);
        Claims claims = jwtServices.extractAllClaims(token);
        String userId = (String) claims.get("id");
        MediaContent md = mediaContentService.getMediaContentById(movieId);
        favoriteService.createFavorites(md, userId);
        Map<String, Object> map = new HashMap<>();
        map.put("message", "ok!!");

        return ResponseEntity.ok(map);
    }


    @GetMapping
    public ResponseEntity<?> getMyFavorites(@RequestHeader(name = "Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7);
        Claims claims = jwtServices.extractAllClaims(token);
        String userId = (String) claims.get("id");
        List<Favorites> favorites = favoriteService.getFavoritesByUserId(userId);
        List<ResultMultimedia> results = new ArrayList<>();
        for(Favorites fav : favorites){
            MediaContent mediaContent = fav.getMediaContent();
            ResultMultimedia resultMultimedia = new ResultMultimedia();
            resultMultimedia.setImdbId(mediaContent.getId());
            resultMultimedia.setTitle(mediaContent.getName());
            resultMultimedia.setType(mediaContent.getType());
            resultMultimedia.setRank(mediaContent.getRanking());
            resultMultimedia.setYear(mediaContent.getYear());
            MultimediaImage multimediaImage = new MultimediaImage();
            multimediaImage.setImageUrl(mediaContent.getImgurl());
            resultMultimedia.setImage(multimediaImage);
            results.add(resultMultimedia);
        }

        return ResponseEntity.ok(results);
    }
}
