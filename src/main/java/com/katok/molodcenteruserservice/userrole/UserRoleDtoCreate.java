package com.katok.molodcenteruserservice.userrole;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDtoCreate {
    @NotNull
    private Long youthCenterId;
    @NotNull
    private Long userId;
    @NotNull
    private Short role;
}
