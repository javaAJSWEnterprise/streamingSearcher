package com.streamingsearcher.services;


import com.streamingsearcher.entities.Favorites;
import com.streamingsearcher.models.MediaContent;
import com.streamingsearcher.models.MediaContentPlatform;
import com.streamingsearcher.models.MultimediaPlatforms.Content;
import com.streamingsearcher.models.MultimediaPlatforms.MultimediaImage;
import com.streamingsearcher.models.MultimediaPlatforms.ResultMultimedia;
import com.streamingsearcher.models.MultimediaPlatforms.StreamingInfo;
import com.streamingsearcher.models.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ApiServiceImpl titleService;

    @Autowired
    private MediaContentServiceImpl mediaContentService;

    @Autowired
    private PlatformServiceImpl platformService;

    @Autowired
    private MediaContentPlatformServiceImpl mediaContentPlatformService;

    @Autowired
    private  FavoriteServiceImpl favoriteService;

    @Override
    public ResultMultimedia getMediaContentById(String imdbId) {
        MediaContent mediaContent = mediaContentService.getMediaContentById(imdbId);

        if(mediaContent != null && mediaContent.isPlatformload()){ // No tengo que hacer nada
            ResultMultimedia resultMultimedia = mapMediaContentToResultMultimedia(mediaContent);

            List<MediaContentPlatform> platforms = mediaContentPlatformService.getMediaContentPlatformsByContentId(imdbId);
            Set<StreamingInfo> streamingInfo = new HashSet<>();
            for (MediaContentPlatform mediaContentPlatform: platforms){
                StreamingInfo info = new StreamingInfo();
                info.setService(mediaContentPlatform.getPlatform().getApiId());
                streamingInfo.add(info);
            }
            Map<String, Set<StreamingInfo>> mapPlatforms = new HashMap<>();
            mapPlatforms.put("ar", streamingInfo);
            resultMultimedia.setStreamingInfo(mapPlatforms);

            return resultMultimedia;

        }
        else if (mediaContent != null ) { // Tengo que cargar las plataformas porque faltan
            Content content = titleService.getMultimediaById(imdbId);
            if(content == null){
               return null;
            }
            ResultMultimedia result = content.getResult();

            Set<String> serviceValues = new HashSet<>();
            if (result.getStreamingInfo() != null) {
                for (Set<StreamingInfo> streamingInfoSet : result.getStreamingInfo().values()) {

                    if(streamingInfoSet == null){
                       return  null;
                    }

                    for (StreamingInfo streamingInfo : streamingInfoSet) {
                        serviceValues.add(streamingInfo.getService());
                    }
                }
            }
            MediaContent mediaContent1 = mediaContentService.getMediaContentById(imdbId);
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
            return result;
        }
        else {
            Content content = titleService.getMultimediaById(imdbId);
            if(content == null){
                return null;
            }
            ResultMultimedia result = content.getResult();

            MediaContent mediaContent1 = mapResultMultimediaToMediaContent(result, true, false);
            Set<String> serviceValues = new HashSet<>();
            if (result.getStreamingInfo() != null) {
                for (Set<StreamingInfo> streamingInfoSet : result.getStreamingInfo().values()) {
                    if(streamingInfoSet == null){
                        return null;
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
            return result;
        }
    }

    @Override
    public List<ResultMultimedia> getMediaContentsByTitle(String title, String userId) {
        List<ResultMultimedia> results = titleService.findTitles(title).getResults();
        List<ResultMultimedia> validResults = results.stream()
                .filter(result -> result.getImage() != null)
                .collect(Collectors.toList());

        for(ResultMultimedia result : validResults){
            MediaContent mediaContent1 = mapResultMultimediaToMediaContent(result, false, true);
            mediaContentService.createMediaContent(mediaContent1);
        }

        if(!userId.isEmpty()){
            List<Favorites> favs = favoriteService.getFavoritesByUserId(userId);
            for(Favorites fav : favs){
                for(ResultMultimedia multi : validResults){
                    if(fav.getMediaContent().getId().equals(multi.getImdbId())){
                        multi.setFav(true);
                    }
                }
            }
        }

        return validResults;
    }

    public ResultMultimedia mapMediaContentToResultMultimedia(MediaContent mediaContent){
        ResultMultimedia resultMultimedia = new ResultMultimedia();
        resultMultimedia.setImdbId(mediaContent.getId());
        resultMultimedia.setTitle(mediaContent.getName());
        resultMultimedia.setType(mediaContent.getType());
        resultMultimedia.setRank(mediaContent.getRanking());
        resultMultimedia.setYear(mediaContent.getYear());
        MultimediaImage multimediaImage = new MultimediaImage();
        multimediaImage.setImageUrl(mediaContent.getImgurl());
        resultMultimedia.setImage(multimediaImage);
        return resultMultimedia;
    }

    public MediaContent mapResultMultimediaToMediaContent(ResultMultimedia result, boolean platformState, boolean loadImage){
        MediaContent mediaContent = new MediaContent();
        mediaContent.setName(result.getTitle());
        mediaContent.setId(result.getImdbId());
        mediaContent.setPlatformload(platformState);
        mediaContent.setType(result.getType());
        mediaContent.setRanking(result.getRank());
        mediaContent.setYear(result.getYear());
        if(loadImage){
            mediaContent.setImgurl(result.getImage().getImageUrl());
        }
        return mediaContent;
    }
}
