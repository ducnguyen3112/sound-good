package com.starscream.soundgood.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Data
public class UserSound extends Auditable {
    @EmbeddedId
    UserSoundId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private AppUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("soundId")
    private Sound sound;
}
