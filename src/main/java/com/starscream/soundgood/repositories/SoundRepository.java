package com.starscream.soundgood.repositories;

import com.starscream.soundgood.entities.Sound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoundRepository extends JpaRepository<Sound, Long> {
    Page<Sound> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);
}
