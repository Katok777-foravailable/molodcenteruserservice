package com.katok.molodcenteruserservice.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventCreateDto {
    @NotNull
    private Long youthCenterId;
    @NotNull
    private Long categoryId;
    @NotBlank
    private String name;
    private String description;
    @NotNull
    private OffsetDateTime startDateTime;
    @NotBlank
    private OffsetDateTime endDateTime;
}
