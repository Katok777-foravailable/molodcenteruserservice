package com.katok.molodcenteruserservice.usereventsubscription;

import com.katok.molodcenteruserservice.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_event_subscriptions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEventSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "youth_center_id", nullable = false)
    private Long youthCenterId;
}
