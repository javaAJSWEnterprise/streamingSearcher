package com.streamingsearcher.models.MultimediaPlatforms;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class D {
    @JsonAlias("d")
    private List<ResultMultimedia> results;
}
