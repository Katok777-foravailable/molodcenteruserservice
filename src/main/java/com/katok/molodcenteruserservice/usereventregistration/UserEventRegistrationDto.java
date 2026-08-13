package com.katok.molodcenteruserservice.usereventregistration;

import com.katok.molodcenteruserservice.user.User;
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
                userEventRegistration.getUser(),
                userEventRegistration.getEventId()
        );
    }

    private Long id;
    private User userId;
    private Long eventId;
}
