package com.katok.molodcenteruserservice.usereventsubscription;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEventSubscriptionDtoCreate {
    @NotNull
    private Long userId;
    @NotNull
    private Long categoryId;
}
