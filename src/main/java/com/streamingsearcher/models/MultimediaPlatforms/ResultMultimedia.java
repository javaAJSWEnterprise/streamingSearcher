package com.streamingsearcher.models.MultimediaPlatforms;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class ResultMultimedia {
    @JsonProperty("type")
    private String tipo;
    @JsonProperty("title")
    private String title;
    @JsonProperty("streamingInfo")
    private Map<String, List<Map<String, Object>>> streamingInfo;
    @JsonProperty("year")
    private int year;
    @JsonProperty("imdbId")
    private String imdbId;
    @JsonProperty("tmdbId")
    private int tmdbId;

    public String getType() {
        return tipo;
    }

    public void setType(String type) {
        this.tipo = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, List<Map<String, Object>>> getStreamingInfo() {
        return streamingInfo;
    }

    public void setStreamingInfo(Map<String, List<Map<String, Object>>> streamingInfo) {
        this.streamingInfo = streamingInfo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public int getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(int tmdbId) {
        this.tmdbId = tmdbId;
    }
}