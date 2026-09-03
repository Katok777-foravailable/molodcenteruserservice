package com.katok.molodcenteruserservice.favouriteyouthcenter;

import com.katok.molodcenteruserservice.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favourite_youth_centers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavouriteYouthCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "youth_center_id", nullable = false)
    private Long youthCenterId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
