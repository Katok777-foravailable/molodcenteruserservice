package com.katok.molodcenteruserservice.favouriteyouthcenter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteYouthCenterRepository extends JpaRepository<FavouriteYouthCenter, Long> {
    @Query("""
            SELECT r FROM FavouriteYouthCenter r
                                 WHERE (:userId IS NULL OR r.user.id = :userId)
                                   AND (:youthCenterId IS NULL OR r.youthCenterId = :youthCenterId)""")
    Page<FavouriteYouthCenter> findFavouriteYouthCentersByYouthCenterIdAndUserId(
            @Param("userId") Long userId,
            @Param("youthCenterId") Long youthCenterId,
            Pageable pageable
    );
}
