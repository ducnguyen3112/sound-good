package com.starscream.soundgood.repositories;

import com.starscream.soundgood.entities.UserSound;
import com.starscream.soundgood.entities.UserSoundId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSoundRepository extends JpaRepository<UserSound, UserSoundId> {
    Optional<UserSound> findById_UserIdAndId_SoundId(Long userId, Long soundId);
}
