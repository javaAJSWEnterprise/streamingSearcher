package com.streamingsearcher.models.MultimediaPlatforms;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Content {

    private ResultMultimedia result;

    public ResultMultimedia getResult() {
        return result;
    }

    public void setResult(ResultMultimedia result) {
        this.result = result;
    }
}
