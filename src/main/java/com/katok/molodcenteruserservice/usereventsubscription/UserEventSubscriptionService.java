package com.katok.molodcenteruserservice.usereventsubscription;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventSubscriptionService {
    private final UserEventSubscriptionRepository userEventSubscriptionRepository;

    public Page<UserEventSubscription> getUserEventSubscriptions(Pageable pageable) {
        return userEventSubscriptionRepository.findAll(pageable);
    }

    public Page<UserEventSubscription> getUserEventSubscriptionsByCategoryIdAndUserId(@Nullable Long categoryId, @Nullable Long userId, Pageable pageable) {
        return userEventSubscriptionRepository.findUserEventSubscriptionsByCategoryIdAndUserId(categoryId, userId, pageable);
    }

    public UserEventSubscription subscribeUser(UserEventSubscription UserEventSubscription) {
        return userEventSubscriptionRepository.save(UserEventSubscription);
    }

    public UserEventSubscription getUserSubscriptionById(Long id) {
        return userEventSubscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User event subscriptions with id " + id + " undefined"));
    }
}
