package com.streamingsearcher.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.Set;

@AllArgsConstructor
@Entity
@Table(name = "platforms")
@Getter
@Setter
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apiId;
    private String name;

    public Platform(){

    }

    public Platform(String apiId ,String name){
        this.apiId = apiId;
        this.name = name;
    }

    @ManyToMany
    @JoinTable(
            name = "media_content_platform",
            joinColumns = @JoinColumn(name = "platform_id"),
            inverseJoinColumns = @JoinColumn(name = "media_content_id")
    )
    private Set<MediaContent> mediaContents;
}
