package com.streamingsearcher.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Title {

    @JsonProperty("id")
    private int id;
    @JsonProperty("q")
    private String name;
    @JsonProperty("qid")
    private String type;
}
