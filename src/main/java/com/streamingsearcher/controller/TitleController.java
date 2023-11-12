package com.streamingsearcher.controller;

import com.streamingsearcher.entities.Favorites;
import com.streamingsearcher.models.MediaContent;
import com.streamingsearcher.models.MediaContentPlatform;
import com.streamingsearcher.models.MultimediaPlatforms.Content;
import com.streamingsearcher.models.MultimediaPlatforms.MultimediaImage;
import com.streamingsearcher.models.MultimediaPlatforms.ResultMultimedia;
import com.streamingsearcher.models.MultimediaPlatforms.StreamingInfo;
import com.streamingsearcher.models.Platform;
import com.streamingsearcher.security.JwtServices;
import com.streamingsearcher.services.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;


import java.util.*;


@RestController
@RequestMapping("/v1/title")
public class TitleController {

    @Autowired
    private TitleServiceImpl titleService;

    @Autowired
    private MediaContentServiceImpl mediaContentService;

    @Autowired
    private PlatformServiceImpl platformService;


    @Autowired
    private MediaContentPlatformServiceImpl mediaContentPlatformService;

    @Autowired
    private JwtServices jwtServices;

    @Autowired
    private FavoriteServiceImpl favoriteService;

   /* @GetMapping
    public ResponseEntity<?> findTitles(@RequestParam(name = "name")String name) {
        titleService.findTitles(name);

        return titleService.findTitles(name);
    }*/

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getInfoById(@PathVariable String id) {
        //titleService.getInfoTitle(id);
        MediaContent mediaContent = mediaContentService.getMediaContentById(id);

        if(mediaContent != null && mediaContent.isPlatformload()){ // No tengo que hacer nada
            ResultMultimedia resultMultimedia = new ResultMultimedia();
            resultMultimedia.setImdbId(mediaContent.getId());
            resultMultimedia.setTitle(mediaContent.getName());
            resultMultimedia.setType(mediaContent.getType());
            resultMultimedia.setRank(mediaContent.getRanking());
            resultMultimedia.setYear(mediaContent.getYear());
            MultimediaImage multimediaImage = new MultimediaImage();
            multimediaImage.setImageUrl(mediaContent.getImgurl());
            resultMultimedia.setImage(multimediaImage);

            List<MediaContentPlatform> platforms = mediaContentPlatformService.getMediaContentPlatformsByContentId(id);
            Set<StreamingInfo> streamingInfo = new HashSet<>();
            for (MediaContentPlatform mediaContentPlatform: platforms){
                StreamingInfo info = new StreamingInfo();
                info.setService(mediaContentPlatform.getPlatform().getApiId());
                streamingInfo.add(info);
            }
            Map<String, Set<StreamingInfo>> mapPlatforms = new HashMap<>();
            mapPlatforms.put("ar", streamingInfo);
            resultMultimedia.setStreamingInfo(mapPlatforms);

            return ResponseEntity.ok(resultMultimedia);

        }
        else if (mediaContent != null ) { // Tengo que cargar las plataformas porque faltan
            Content content = titleService.getMultimediaById(id);
            if(content == null){
                Map<String, String> responseMessage = new HashMap<>();
                responseMessage.put("message", "media content not found");
                return ResponseEntity.badRequest().body(responseMessage);
            }
            ResultMultimedia result = content.getResult();

            Set<String> serviceValues = new HashSet<>();
            if (result.getStreamingInfo() != null) {
                for (Set<StreamingInfo> streamingInfoSet : result.getStreamingInfo().values()) {

                    if(streamingInfoSet == null){
                        Map<String, String> responseMessage = new HashMap<>();
                        responseMessage.put("message", "platforms not found");
                        return ResponseEntity.badRequest().body(responseMessage);
                    }

                    for (StreamingInfo streamingInfo : streamingInfoSet) {
                        serviceValues.add(streamingInfo.getService());
                    }
                }
            }
            MediaContent mediaContent1 = mediaContentService.getMediaContentById(id);
            Set<Platform> platforms = platformService.findPlatformsByServiceValues(serviceValues);
            for (Platform platform : platforms) {
                mediaContentPlatformService.createMediaContentPlatform(new MediaContentPlatform(mediaContent1, platform));
            }

            mediaContent1.setPlatformload(true);
            mediaContentService.updateMediaContent(mediaContent1);
            MultimediaImage image = new MultimediaImage();
            image.setImageUrl(mediaContent1.getImgurl());
            result.setImage(image);
            result.setRank(mediaContent1.getRanking());
            return ResponseEntity.ok(result);
        }
        else {
            Content content = titleService.getMultimediaById(id);
            if(content == null){
                Map<String, String> responseMessage = new HashMap<>();
                responseMessage.put("message", "media content not found");
                return ResponseEntity.badRequest().body(responseMessage);
            }
            ResultMultimedia result = content.getResult();

            MediaContent mediaContent1 = new MediaContent();
            mediaContent1.setName(result.getTitle());
            mediaContent1.setId(result.getImdbId());
            mediaContent1.setPlatformload(true);
            mediaContent1.setType(result.getType());
            mediaContent1.setRanking(result.getRank());
            mediaContent1.setYear(result.getYear());
            Set<String> serviceValues = new HashSet<>();
            if (result.getStreamingInfo() != null) {
                for (Set<StreamingInfo> streamingInfoSet : result.getStreamingInfo().values()) {
                    if(streamingInfoSet == null){
                        Map<String, String> responseMessage = new HashMap<>();
                        responseMessage.put("message", "platforms not found");
                        return ResponseEntity.badRequest().body(responseMessage);
                    }
                    for (StreamingInfo streamingInfo : streamingInfoSet) {
                        serviceValues.add(streamingInfo.getService());
                    }
                }
            }
            Set<Platform> platforms = platformService.findPlatformsByServiceValues(serviceValues);
            mediaContentService.createMediaContent(mediaContent1);
            for (Platform platform : platforms) {
                mediaContentPlatformService.createMediaContentPlatform(new MediaContentPlatform(mediaContent1, platform));
            }

            return ResponseEntity.ok(result);
        }


    }


    @GetMapping("/{title}")
    public ResponseEntity<?> getInfoByTitle(@PathVariable String title,  @RequestHeader(name = "Authorization", required = false, defaultValue = "") String authorizationHeader) {

        List<ResultMultimedia> results = titleService.findTitles(title).getResults();
        // Crear una nueva lista que contiene solo los elementos válidos
        List<ResultMultimedia> validResults = results.stream()
                .filter(result -> result.getImage() != null)
                .collect(Collectors.toList());

        for(ResultMultimedia result : validResults){
            MediaContent mediaContent1 = new MediaContent();
            mediaContent1.setName(result.getTitle());
            mediaContent1.setId(result.getImdbId());
            mediaContent1.setPlatformload(false);
            mediaContent1.setType(result.getType());
            mediaContent1.setRanking(result.getRank());
            mediaContent1.setYear(result.getYear());
            mediaContent1.setImgurl(result.getImage().getImageUrl());
            mediaContentService.createMediaContent(mediaContent1);
        }
        if(!authorizationHeader.isEmpty()){
            try{
                String token = authorizationHeader.substring(7);
                Claims claims = jwtServices.extractAllClaims(token);
                String userId = (String) claims.get("id");
                if(!userId.equals("")){
                    List<Favorites> favs = favoriteService.getFavoritesByUserId(userId);
                    for(Favorites fav : favs){
                        for(ResultMultimedia multi : validResults){
                            if(fav.getMediaContent().getId().equals(multi.getImdbId())){
                                multi.setFav(true);
                            }
                        }
                    }
                }
            }catch (JwtException e){
                return ResponseEntity.ok(validResults);
            }
        }
        return ResponseEntity.ok(validResults);
    }


}
