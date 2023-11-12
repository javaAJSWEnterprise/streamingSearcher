package com.streamingsearcher.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.streamingsearcher.entities.Favorites;
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

    @JsonBackReference
    @OneToMany(mappedBy = "mediaContent", cascade = CascadeType.ALL)
    private Set<MediaContentPlatform> mediaContentPlatforms;

    @JsonBackReference
    @OneToMany(mappedBy = "mediaContent", cascade = CascadeType.ALL)
    private Set<Favorites> favorites;
}
