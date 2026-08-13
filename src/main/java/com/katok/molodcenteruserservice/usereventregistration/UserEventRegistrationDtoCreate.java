package com.katok.molodcenteruserservice.usereventregistration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEventRegistrationDtoCreate {
    private Long userId;
    private Long eventId;
}
