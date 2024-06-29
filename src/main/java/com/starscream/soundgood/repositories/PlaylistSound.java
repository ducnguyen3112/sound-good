package com.starscream.soundgood.repositories;

import com.starscream.soundgood.entities.Auditable;
import com.starscream.soundgood.entities.Playlist;
import com.starscream.soundgood.entities.Sound;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistSound extends Auditable {
    @EmbeddedId
    PlaylistSoundId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("playlistId")
    private Playlist playlist;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("soundId")
    private Sound sound;
}
