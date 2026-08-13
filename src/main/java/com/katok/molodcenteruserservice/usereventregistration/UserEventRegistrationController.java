package com.katok.molodcenteruserservice.usereventregistration;

import com.katok.molodcenteruserservice.user.User;
import com.katok.molodcenteruserservice.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/registration")
public class UserEventRegistrationController {
    private final UserEventRegistrationService userEventRegistrationService;
    private final UserService userService;

    @GetMapping
    public Page<UserEventRegistrationDto> getRegistrationsUserEvent(
            @RequestParam(required = false) Long eventId,
            @RequestParam(defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, 10);

        return userEventRegistrationService.getUsersEventRegistrationByEventIdAndUserId(eventId, null, pageable).map(UserEventRegistrationDto::toUserEventRegistrationDto);
    }

    @GetMapping("/{id}")
    public UserEventRegistrationDto getRegistrationUserEvent(@PathVariable Long id) {
        return UserEventRegistrationDto.toUserEventRegistrationDto(userEventRegistrationService.getUserRegistrationById(id));
    }

    @PostMapping
    public UserEventRegistrationDto registerUserEvent(UserEventRegistrationDtoCreate userEventRegistrationDtoCreate) {
        User user = userService.getUserById(userEventRegistrationDtoCreate.getUserId());

        UserEventRegistration userEventRegistration = UserEventRegistration.builder()
                .eventId(userEventRegistrationDtoCreate.getEventId())
                .user(user)
                .build();

        return UserEventRegistrationDto.toUserEventRegistrationDto(userEventRegistrationService.registerUser(userEventRegistration));
    }
}
