package com.katok.molodcenteruserservice.favouriteyouthcenter;

import com.katok.molodcenteruserservice.exception.ValueNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavouriteYouthCenterService {
    private final FavouriteYouthCenterRepository favouriteYouthCenterRepository;

    public FavouriteYouthCenter addFavouriteYouthCenter(FavouriteYouthCenter favouriteYouthCenter) {
        if (!getFavouriteYouthCentersByYouthCenterIdAndUserId(favouriteYouthCenter.getYouthCenterId(), favouriteYouthCenter.getUser().getId(), PageRequest.of(0, 1)).isEmpty()) {
            throw new IllegalArgumentException("Молодіжний центр з айді " + favouriteYouthCenter.getYouthCenterId() + " вже є обраним користувачем з айді " + favouriteYouthCenter.getUser().getId() + "!");
        }

        return favouriteYouthCenterRepository.save(favouriteYouthCenter);
    }

    public Page<FavouriteYouthCenter> getFavouriteYouthCentersByYouthCenterIdAndUserId(Long youthCenterId, Long userId, Pageable pageable) {
        return favouriteYouthCenterRepository.findFavouriteYouthCentersByYouthCenterIdAndUserId(youthCenterId, userId, pageable);
    }

    public FavouriteYouthCenter getFavouriteYouthCenterById(Long id) {
        return favouriteYouthCenterRepository.findById(id)
                .orElseThrow(() -> new ValueNotFound("Обраного молодіжного центру з айді " + id + " не знайдено!"));
    }

    public void deleteFavouriteYouthCenterById(Long id) {
        favouriteYouthCenterRepository.deleteById(id);
    }
}
