package com.streamingsearcher.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "media_content")
@Getter
@Setter
@AllArgsConstructor
public class MediaContent {
    @Id
    private String id;
    private String name;
    private String type;
    private int ranking;
    private int year;
    private String imgurl;
    private boolean platformload;

    public MediaContent() {

    }

    @ManyToMany(mappedBy = "mediaContents")
    private Set<Platform> platforms;
}
