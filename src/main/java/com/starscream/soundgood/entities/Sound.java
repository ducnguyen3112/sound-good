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
public class Sound extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    @ManyToOne
    AppUser uploadedBy;
    String soundPath;
    String coverPath;
    @OneToMany(mappedBy = "sound", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<UserSound> favoriteBy;
    @OneToMany(mappedBy = "sound", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<PlaylistSound> playlistSounds;
}
