package com.katok.molodcenteruserservice.usereventsubscription;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEventSubscriptionRepository extends JpaRepository<UserEventSubscription, Long> {
    @Query("SELECT r FROM UserEventSubscription r WHERE (:categoryId IS NULL OR r.categoryId = :categoryId) AND (:userId IS NULL OR r.user.id = :userId)")
    Page<UserEventSubscription> findUserEventSubscriptionsByCategoryIdAndUserId(
            @Param("categoryId") Long categoryId,
            @Param("userId") Long userId,
            Pageable pageable);
}
