package com.katok.molodcenteruserservice.userrole;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRoleDto {
    public static UserRoleDto toUserRoleDto(UserRole userRole) {
        return new UserRoleDto(
                userRole.getId(),
                userRole.getYouthCenterId(),
                userRole.getUser().getId(),
                userRole.getRole()
        );
    }

    private Long id;
    private Long youthCenterId;
    private Long userId;
    private short role;
}
