package com.streamingsearcher.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
/*@JsonPropertyOrder({
        "ar"
})*/
public class StreamingInfo {

    public StreamingInfo() {
    }

    @JsonProperty("ar")
    private List<Ar> ar;

    @JsonProperty("ar")
    public List<Ar> getAr() {
        return ar;
    }

    @JsonProperty("ar")
    public void setAr(List<Ar> ar) {
        this.ar = ar;
    }
}
