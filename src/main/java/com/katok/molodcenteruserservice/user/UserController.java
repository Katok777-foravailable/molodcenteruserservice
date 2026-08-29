package com.katok.molodcenteruserservice.user;

import com.katok.molodcenteruserservice.usereventregistration.UserEventRegistrationDto;
import com.katok.molodcenteruserservice.usereventregistration.UserEventRegistrationService;
import com.katok.molodcenteruserservice.usereventsubscription.UserEventSubscriptionDto;
import com.katok.molodcenteruserservice.usereventsubscription.UserEventSubscriptionService;
import com.katok.molodcenteruserservice.userrole.UserRoleDto;
import com.katok.molodcenteruserservice.userrole.UserRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserEventRegistrationService userEventRegistrationService;
    private final UserEventSubscriptionService userEventSubscriptionService;
    private final UserRoleService userRoleService;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return UserDto.toUserDto(userService.getUserById(id));
    }

    @GetMapping("/{id}/registration")
    public Page<UserEventRegistrationDto> getUserRegistrations(@PathVariable Long id,
                                                               @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return userEventRegistrationService.getUsersEventRegistrationByEventIdAndUserId(null, id, pageable).map(UserEventRegistrationDto::toUserEventRegistrationDto);
    }

    @GetMapping("/{id}/subscription")
    public Page<UserEventSubscriptionDto> getUserSubscriptions(@PathVariable Long id,
                                                               @RequestParam(required = false) Long categoryId,
                                                               @RequestParam(required = false) Long youthCenterId,
                                                               @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return userEventSubscriptionService.getUserEventSubscriptionsByCategoryIdAndUserIdAndYouthCenterId(categoryId, id, youthCenterId, pageable).map(UserEventSubscriptionDto::toUserEventSubscriptionDto);
    }

    @GetMapping("/{id}/role")
    public Page<UserRoleDto> getUserSubscriptions(@PathVariable Long id,
                                                  @RequestParam(required = false) Long youthCenterId,
                                                  @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return userRoleService.getUserRoleByUserIdAndYouthCenter(id, youthCenterId, pageable).map(UserRoleDto::toUserRoleDto);
    }

    @GetMapping("/search")
    public ResponseEntity<UserDto> getUser(@RequestParam(required = false) Long telegramId,
                                          @RequestParam(required = false) String phoneNumber,
                                           @RequestParam(required = false) String externalId) {
        if (telegramId != null) {
            return ResponseEntity.ok(UserDto.toUserDto(userService.getUserByTelegramUserId(telegramId)));
        }

        if (phoneNumber != null) {
            return ResponseEntity.ok(UserDto.toUserDto(userService.getUserByPhoneNumber(phoneNumber)));
        }

        if (externalId != null) {
            return ResponseEntity.ok(UserDto.toUserDto(userService.getUserByExternalId(externalId)));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping
    public Page<UserDto> getUsers(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);

        return userService.getUsers(pageable).map(UserDto::toUserDto);
    }

    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserDtoCreate userDtoCreate) {
        User userDetails = User.builder()
                .telegramUserId(userDtoCreate.getTelegramUserId())
                .name(userDtoCreate.getName())
                .lastName(userDtoCreate.getLastName())
                .phoneNumber(userDtoCreate.getPhoneNumber())
                .build();

        return UserDto.toUserDto(userService.createUser(userDetails));
    }
}
