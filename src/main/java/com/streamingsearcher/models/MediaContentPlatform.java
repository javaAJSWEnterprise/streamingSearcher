package com.streamingsearcher.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "media_content_platform")
@Getter
@Setter
@AllArgsConstructor
public class MediaContentPlatform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "media_content_id")
    private MediaContent mediaContent;

    @ManyToOne
    @JoinColumn(name = "platform_id")
    private Platform platform;

    public MediaContentPlatform() {
    }

    public MediaContentPlatform(MediaContent mediaContent, Platform platform) {
        this.mediaContent = mediaContent;
        this.platform = platform;
    }
}
