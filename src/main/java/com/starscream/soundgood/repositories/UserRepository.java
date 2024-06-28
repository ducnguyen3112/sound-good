package com.starscream.soundgood.repositories;

import com.starscream.soundgood.entities.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    Boolean existsByUsername(String username);
    Optional<AppUser> findByUsername(String username);

    Page<AppUser> findAllByUsernameContainingIgnoreCase(String keyword,
                                                        Pageable pageRequest);
}
