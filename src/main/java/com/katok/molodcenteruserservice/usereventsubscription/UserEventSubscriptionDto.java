package com.katok.molodcenteruserservice.usereventsubscription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEventSubscriptionDto {
    public static UserEventSubscriptionDto toUserEventSubscriptionDto(UserEventSubscription userEventSubscription) {
        return new UserEventSubscriptionDto(
                userEventSubscription.getId(),
                userEventSubscription.getUser().getId(),
                userEventSubscription.getCategoryId()
        );
    }

    private Long id;
    private Long userId;
    private Long categoryId;
}
