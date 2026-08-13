package com.katok.molodcenteruserservice.usereventregistration;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEventRegistrationRepository extends JpaRepository<UserEventRegistration, Long> {
    @Query("SELECT r FROM UserEventRegistration r WHERE (:eventId IS NULL OR r.eventId = :eventId) AND (:userId IS NULL OR r.user.id = :userId)")
    Page<UserEventRegistration> findUserEventRegistrationByEventIdAndUserId(
            @Param("eventId") Long eventId,
            @Param("userId") Long userId,
            Pageable pageable);
}
