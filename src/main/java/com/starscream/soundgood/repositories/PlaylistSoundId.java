package com.starscream.soundgood.repositories;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistSoundId implements Serializable {
    private Long playlistId;
    private Long soundId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistSoundId that = (PlaylistSoundId) o;
        return Objects.equals(playlistId, that.playlistId) && Objects.equals(soundId, that.soundId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, soundId);
    }
}
