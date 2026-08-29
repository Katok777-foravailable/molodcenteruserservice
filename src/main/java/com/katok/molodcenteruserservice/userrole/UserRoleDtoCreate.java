package com.katok.molodcenteruserservice.userrole;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRoleDtoCreate {
    @NotNull
    private Long youthCenterId;
    @NotNull
    private Long userId;
    @NotNull
    private short role;
}
