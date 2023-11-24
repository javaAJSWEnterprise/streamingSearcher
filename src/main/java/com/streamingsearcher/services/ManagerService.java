package com.streamingsearcher.services;

import com.streamingsearcher.models.MediaContent;
import com.streamingsearcher.models.MultimediaPlatforms.ResultMultimedia;
import java.util.List;

public interface ManagerService {
    ResultMultimedia getMediaContentById(String imdbId);
    List<ResultMultimedia> getMediaContentsByTitle(String title, String userId);
    ResultMultimedia mapMediaContentToResultMultimedia(MediaContent mediaContent);
    MediaContent mapResultMultimediaToMediaContent(ResultMultimedia result, boolean platformState, boolean loadImage);
}
