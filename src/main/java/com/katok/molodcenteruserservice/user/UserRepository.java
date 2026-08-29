package com.katok.molodcenteruserservice.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByPhoneNumber(String phoneNumber);
    Optional<User> findUserByTelegramUserId(Long telegramUserId);
    Optional<User> findUserByExternalId(String externalId);
    Page<User> findUsersByAdminRank(Short adminRank, Pageable pageable);
}
