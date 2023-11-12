package com.streamingsearcher.entities;


import com.streamingsearcher.models.MediaContent;
import com.streamingsearcher.models.Platform;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Favorites")
public class Favorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "media_content_id")
    private MediaContent mediaContent;

    @Column(name = "user_id")
    private String userId;

    public Favorites() {
    }

    public Favorites(MediaContent mediaContent, String userId) {
        this.mediaContent = mediaContent;
        this.userId = userId;
    }
}
