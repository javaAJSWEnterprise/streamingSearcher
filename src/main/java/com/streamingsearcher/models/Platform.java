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

    @OneToMany(mappedBy = "platform", cascade = CascadeType.ALL)
    private Set<MediaContentPlatform> mediaContentPlatforms;

}
