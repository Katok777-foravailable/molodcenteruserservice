package com.katok.molodcenteruserservice.usereventsubscription;

import com.katok.molodcenteruserservice.user.User;
import com.katok.molodcenteruserservice.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/subscriptions")
public class UserEventSubscriptionController {
    private final UserEventSubscriptionService userEventSubscriptionService;
    private final UserService userService;

    @PostMapping
    public UserEventSubscriptionDto subscribeUser(@Valid @RequestBody UserEventSubscriptionDtoCreate userEventSubscriptionDtoCreate) {
        User userDetails = userService.getUserById(userEventSubscriptionDtoCreate.getUserId());

        UserEventSubscription userEventSubscriptionDetails = UserEventSubscription.builder()
                .categoryId(userEventSubscriptionDtoCreate.getCategoryId())
                .user(userDetails)
                .build();

        return UserEventSubscriptionDto.toUserEventSubscriptionDto(userEventSubscriptionService.subscribeUser(userEventSubscriptionDetails));
    }

    @GetMapping("/{id}")
    public UserEventSubscriptionDto getUserSubscriptionById(@PathVariable Long id) {
        return UserEventSubscriptionDto.toUserEventSubscriptionDto(userEventSubscriptionService.getUserSubscriptionById(id));
    }

    @GetMapping
    public Page<UserEventSubscriptionDto> getUsersSubscriptions(@RequestParam(required = false) Long categoryId,
                                                                @RequestParam(required = false) Long userId,
                                                                @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);

        return userEventSubscriptionService.getUserEventSubscriptionsByCategoryIdAndUserId(categoryId, userId, pageable).map(UserEventSubscriptionDto::toUserEventSubscriptionDto);
    }
}
