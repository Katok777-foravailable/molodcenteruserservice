package com.katok.molodcenteruserservice.favouriteyouthcenter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteYouthCenterDto {
    public static FavouriteYouthCenterDto toFavouriteYouthCenterDto(FavouriteYouthCenter favouriteYouthCenter) {
        return new FavouriteYouthCenterDto(
                favouriteYouthCenter.getId(),
                favouriteYouthCenter.getYouthCenterId(),
                favouriteYouthCenter.getUser().getId()
        );
    }

    private Long id;
    private Long youthCenterId;
    private Long userId;
}
