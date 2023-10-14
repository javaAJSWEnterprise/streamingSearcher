package com.streamingsearcher.models.MultimediaPlatforms;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.streamingsearcher.models.Result;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.streamingsearcher.models.Result;

public class Content {
    public Content() {
    }

    private ResultMultimedia result;

    public ResultMultimedia getResult() {
        return result;
    }

    public void setResult(ResultMultimedia result) {
        this.result = result;
    }
}
