package com.starscream.soundgood.entities;

import com.starscream.soundgood.repositories.PlaylistSound;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Playlist extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    @ManyToOne
    AppUser createdBy;
    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL, orphanRemoval =
            true)
    Set<PlaylistSound> playlistSounds;
}
