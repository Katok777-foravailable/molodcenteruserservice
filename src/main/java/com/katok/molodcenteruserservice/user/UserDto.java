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
                user.getExternalId(),
                user.getName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getAdminRank()
        );
    }

    private Long id;
    private Long telegramUserId;
    private String externalId;
    private String name;
    private String lastName;
    private String phoneNumber;
    private Short adminRank;
}
