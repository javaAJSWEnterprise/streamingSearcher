package com.streamingsearcher.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.Set;

@Entity
@Table(name = "platforms")
@Getter
@Setter
@AllArgsConstructor
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Platform(){

    }

    @ManyToMany
    @JoinTable(
            name = "media_content_platform",
            joinColumns = @JoinColumn(name = "platform_id"),
            inverseJoinColumns = @JoinColumn(name = "media_content_id")
    )
    private Set<MediaContent> mediaContents;
}
