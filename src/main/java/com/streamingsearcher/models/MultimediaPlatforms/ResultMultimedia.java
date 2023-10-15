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
    @JsonAlias("q")
    private String type;
    @JsonProperty("title")
    @JsonAlias("l")
    private String title;
    @JsonProperty("streamingInfo")
    private Map<String, Set<StreamingInfo>> streamingInfo;
    @JsonProperty("year")
    @JsonAlias({"firstAirYear", "y"})
    private int year;
    @JsonProperty("imdbId")
    @JsonAlias("id")
    private String imdbId;
    @JsonProperty("i")
    private MultimediaImage image;
    @JsonProperty("rank")
    private int rank;
}