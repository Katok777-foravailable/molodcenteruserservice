package com.katok.molodcenteruserservice.favouriteyouthcenter;

import com.katok.molodcenteruserservice.user.User;
import com.katok.molodcenteruserservice.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/favourite-youth-center")
public class FavouriteYouthCenterController {
    private final FavouriteYouthCenterService favouriteYouthCenterService;
    private final UserService userService;

    @GetMapping("/{id}")
    public FavouriteYouthCenterDto getFavouriteYouthCenter(@PathVariable Long id) {
        return FavouriteYouthCenterDto.toFavouriteYouthCenterDto(favouriteYouthCenterService.getFavouriteYouthCenterById(id));
    }

    @GetMapping
    public Page<FavouriteYouthCenterDto> getFavouriteYouthCenters(@RequestParam(required = false) Long youthCenterId,
                                                                  @RequestParam(required = false) Long userId,
                                                                  @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);

        return favouriteYouthCenterService.getFavouriteYouthCentersByYouthCenterIdAndUserId(youthCenterId, userId, pageable)
                .map(FavouriteYouthCenterDto::toFavouriteYouthCenterDto);
    }

    @DeleteMapping("/{id}")
    public void deleteFavouriteYouthCenter(@PathVariable Long id) {
        favouriteYouthCenterService.deleteFavouriteYouthCenterById(id);
    }

    @PostMapping
    public FavouriteYouthCenterDto createFavouriteYouthCenter(@RequestBody @Valid FavouriteYouthCenterDtoCreate favouriteYouthCenterDtoCreate) {
        User user = userService.getUserById(favouriteYouthCenterDtoCreate.getUserId());

        FavouriteYouthCenter favouriteYouthCenter = new FavouriteYouthCenter(null, favouriteYouthCenterDtoCreate.getYouthCenterId(), user);
        return FavouriteYouthCenterDto.toFavouriteYouthCenterDto(favouriteYouthCenterService.addFavouriteYouthCenter(favouriteYouthCenter));
    }
}
