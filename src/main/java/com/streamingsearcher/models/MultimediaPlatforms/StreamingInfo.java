package com.streamingsearcher.models.MultimediaPlatforms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamingInfo {
    @JsonProperty("service")
    private String service;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StreamingInfo other = (StreamingInfo) obj;
        return Objects.equals(service, other.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(service);
    }
}
