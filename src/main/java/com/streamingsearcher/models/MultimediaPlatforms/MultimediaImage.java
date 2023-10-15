package com.streamingsearcher.models.MultimediaPlatforms;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MultimediaImage {
    @JsonProperty("url")
    @JsonAlias("imageUrl")
    private String imageUrl;
}
