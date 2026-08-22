package com.katok.molodcenteruserservice.usereventsubscription;

import com.katok.molodcenteruserservice.category.CategoryClient;
import com.katok.molodcenteruserservice.category.CategoryDto;
import com.katok.molodcenteruserservice.exception.ValueNotFound;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserEventSubscriptionService {
    private final UserEventSubscriptionRepository userEventSubscriptionRepository;
    private final CategoryClient categoryClient;

    public Page<UserEventSubscription> getUserEventSubscriptions(Pageable pageable) {
        return userEventSubscriptionRepository.findAll(pageable);
    }

    public Page<UserEventSubscription> getUserEventSubscriptionsByCategoryIdAndUserIdAndYouthCenterId(@Nullable Long categoryId, @Nullable Long userId, @Nullable Long youthCenterId, Pageable pageable) {
        return userEventSubscriptionRepository.findUserEventSubscriptionsByCategoryIdAndUserId(categoryId, userId, youthCenterId, pageable);
    }

    public UserEventSubscription subscribeUser(UserEventSubscription userEventSubscription) {
        CategoryDto category = categoryClient.getCategoryById(userEventSubscription.getCategoryId());
        if (category == null) {
            throw new IllegalArgumentException("Категорії з айді " + userEventSubscription.getCategoryId() + " не існує!");
        }
        if (!Objects.equals(userEventSubscription.getYouthCenterId(), category.getYouthCenterId())) {
            throw new IllegalArgumentException("Молодіжний центр який ви вказали (" + userEventSubscription.getYouthCenterId() + "), відрізняєте від молодіжниго центру в категорії (" + category.getYouthCenterId() + ")!");
        }

        return userEventSubscriptionRepository.save(userEventSubscription);
    }

    public UserEventSubscription getUserSubscriptionById(Long id) {
        return userEventSubscriptionRepository.findById(id)
                .orElseThrow(() -> new ValueNotFound("Підписку на подію з айді " + id + " не знайдено"));
    }
}
