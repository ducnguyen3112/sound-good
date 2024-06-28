package com.starscream.soundgood.repositories;

import com.starscream.soundgood.entities.Sound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SoundRepository extends JpaRepository<Sound, Long> {

    @Query("SELECT s FROM Sound s " +
            "LEFT JOIN UserSound us ON s.id = us.id.soundId " +
            "LEFT JOIN PlaylistSound ps ON s.id = ps.id.soundId " +
            "WHERE " +
            "(:userLiked IS NULL OR us.id.userId = :userLiked) " +
            "AND (:playlistId IS NULL OR ps.id.playlistId = :playlistId) " +
            "AND (:keyword IS NULL OR :keyword = '' OR LOWER(TRIM(s.title)) LIKE LOWER(CONCAT('%', TRIM(:keyword), '%')))")
    Page<Sound> findSongsByCriteria(
            @Param("keyword") String keyword,
            @Param("userLiked") Long userLiked,
            @Param("playlistId") Long playlistId,
            Pageable pageable);
}
