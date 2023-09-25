package com.streamingsearcher.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
/*@JsonPropertyOrder({
        "service",
        "streamingType",
        "link",
        "audios",
        "subtitles",
        "availableSince"
})*/
public class Ar {

    public Ar() {
    }

    @JsonProperty("service")
    private String service;
    @JsonProperty("streamingType")
    private String streamingType;
    @JsonProperty("link")
    private String link;
    @JsonProperty("audios")
    private List<Object> audios;
    @JsonProperty("subtitles")
    private List<Object> subtitles;
    @JsonProperty("availableSince")
    private Integer availableSince;

    @JsonProperty("service")
    public String getService() {
        return service;
    }

    @JsonProperty("service")
    public void setService(String service) {
        this.service = service;
    }

    @JsonProperty("streamingType")
    public String getStreamingType() {
        return streamingType;
    }

    @JsonProperty("streamingType")
    public void setStreamingType(String streamingType) {
        this.streamingType = streamingType;
    }

    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(String link) {
        this.link = link;
    }

    @JsonProperty("audios")
    public List<Object> getAudios() {
        return audios;
    }

    @JsonProperty("audios")
    public void setAudios(List<Object> audios) {
        this.audios = audios;
    }

    @JsonProperty("subtitles")
    public List<Object> getSubtitles() {
        return subtitles;
    }

    @JsonProperty("subtitles")
    public void setSubtitles(List<Object> subtitles) {
        this.subtitles = subtitles;
    }

    @JsonProperty("availableSince")
    public Integer getAvailableSince() {
        return availableSince;
    }

    @JsonProperty("availableSince")
    public void setAvailableSince(Integer availableSince) {
        this.availableSince = availableSince;
    }
}
