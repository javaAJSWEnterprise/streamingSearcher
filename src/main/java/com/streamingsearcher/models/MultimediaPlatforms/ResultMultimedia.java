package com.streamingsearcher.models.MultimediaPlatforms;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;
import java.util.Set;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultMultimedia  {
    @JsonProperty("type")
    private String type;
    @JsonProperty("title")
    private String title;
    @JsonProperty("streamingInfo")
    private Map<String, Set<StreamingInfo>> streamingInfo;
    @JsonProperty("year")
    @JsonAlias("firstAirYear")
    private int year;
    @JsonProperty("imdbId")
    private String imdbId;
}