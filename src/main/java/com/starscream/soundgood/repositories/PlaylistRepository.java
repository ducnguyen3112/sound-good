package com.starscream.soundgood.repositories;

import com.starscream.soundgood.entities.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Set<Playlist> findByCreatedBy_IdOrderByCreatedDateDesc(Long userId);
}
