package com.starscream.soundgood.entities;

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
public class UserSoundId implements Serializable {
    private Long userId;
    private Long soundId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSoundId that = (UserSoundId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(soundId, that.soundId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, soundId);
    }
}
