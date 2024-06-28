package com.starscream.soundgood.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistSoundRepository extends JpaRepository<PlaylistSound,
        PlaylistSoundId> {
}
