package com.streamingsearcher.controller;

import com.streamingsearcher.entities.Favorites;
import com.streamingsearcher.models.MediaContent;
import com.streamingsearcher.models.MultimediaPlatforms.MultimediaImage;
import com.streamingsearcher.models.MultimediaPlatforms.ResultMultimedia;
import com.streamingsearcher.security.JwtServices;
import com.streamingsearcher.services.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/v1/favorite")
public class FavoriteController {

    @Autowired
    private MediaContentServiceImpl mediaContentService;

    @Autowired
    private JwtServices jwtServices;

    @Autowired
    private FavoriteServiceImpl favoriteService;

    @Autowired
    private ManagerServiceImpl managerService;

    @PostMapping("/{movieId}")
    public ResponseEntity<?> createFavorite(@PathVariable String movieId, @RequestHeader(name = "Authorization") String authorizationHeader){
        try {
            String token = authorizationHeader.substring(7);
            Claims claims = jwtServices.extractAllClaims(token);
            String userId = (String) claims.get("id");
            MediaContent md = mediaContentService.getMediaContentById(movieId);
            favoriteService.createFavorites(md, userId);
            Map<String, Object> map = new HashMap<>();
            map.put("message", "ok!!");
            return ResponseEntity.ok(map);
        }catch (JwtException e){
            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (DataAccessException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping
    public ResponseEntity<?> getMyFavorites(@RequestHeader(name = "Authorization") String authorizationHeader){
        try {
            String token = authorizationHeader.substring(7);
            Claims claims = jwtServices.extractAllClaims(token);
            String userId = (String) claims.get("id");
            List<Favorites> favorites = favoriteService.getFavoritesByUserId(userId);
            List<ResultMultimedia> results = new ArrayList<>();
            for(Favorites fav : favorites){
                MediaContent mediaContent = fav.getMediaContent();
                ResultMultimedia resultMultimedia = managerService.mapMediaContentToResultMultimedia(mediaContent);
                resultMultimedia.setFav(true);
                results.add(resultMultimedia);
            }
            return ResponseEntity.ok(results);
        }catch (JwtException e){
            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (DataAccessException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
