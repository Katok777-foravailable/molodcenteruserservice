package com.katok.molodcenteruserservice.usereventregistration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEventRegistrationDto {
    public static UserEventRegistrationDto toUserEventRegistrationDto(UserEventRegistration userEventRegistration) {
        return new UserEventRegistrationDto(
                userEventRegistration.getId(),
                userEventRegistration.getUser().getId(),
                userEventRegistration.getEventId()
        );
    }

    private Long id;
    private Long userId;
    private Long eventId;
}
