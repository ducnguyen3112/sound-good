package com.starscream.soundgood.repositories;

import com.starscream.soundgood.entities.AppUser;
import com.starscream.soundgood.enums.UserStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    Boolean existsByUsername(String username);
    Optional<AppUser> findByUsernameAndStatus(String username, UserStatusEnum status);
    Optional<AppUser> findByUsername(String username);

    Page<AppUser> findAllByUsernameContainingIgnoreCaseAndIdNot(String keyword, Long id, Pageable pageRequest);
    Page<AppUser> findAllByIdNot(Long id, Pageable pageRequest);
}
