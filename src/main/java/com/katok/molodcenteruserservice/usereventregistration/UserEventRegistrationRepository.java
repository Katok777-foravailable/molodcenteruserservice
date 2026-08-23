package com.katok.molodcenteruserservice.usereventregistration;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("SELECT DISTINCT r.eventId FROM UserEventRegistration r WHERE (:lastEventId IS NULL OR r.eventId > :lastEventId) ORDER BY r.eventId ASC")
    Page<Long> findDistinctEventIds(@Param("lastEventId") Long lastEventId, Pageable pageable);

    @Modifying
    @Query(value = """
        DELETE FROM user_event_registrations WHERE id IN (
                                 SELECT id FROM user_event_registrations
                                 WHERE (:userId IS NULL OR user_id = :userId)
                                   AND (:eventId IS NULL OR event_id = :eventId)
                                 LIMIT :limit)
    """, nativeQuery = true)
    int deleteEventRegistrations(
            @Param("userId") Long userId,
            @Param("eventId") Long eventId,
            @Param("limit") int limit
    );
}
