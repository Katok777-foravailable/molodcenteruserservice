package com.katok.molodcenteruserservice.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDtoCreate {
    private Long telegramUserId;
    private String name;
    private String lastName;
    private String phoneNumber;
}
