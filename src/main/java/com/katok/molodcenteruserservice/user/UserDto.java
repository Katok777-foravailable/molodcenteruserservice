package com.katok.molodcenteruserservice.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getTelegramUserId(),
                user.getName(),
                user.getLastName(),
                user.getPhoneNumber()
        );
    }

    private Long id;
    private Long telegramUserId;
    private String name;
    private String lastName;
    private String phoneNumber;
}
