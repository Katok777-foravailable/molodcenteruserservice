package com.katok.molodcenteruserservice.user;

import com.katok.molodcenteruserservice.usereventregistration.UserEventRegistration;
import com.katok.molodcenteruserservice.usereventsubscription.UserEventSubscription;
import com.katok.molodcenteruserservice.userrole.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "telegram_user_id", unique = true)
    private Long telegramUserId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", unique = true, length = 20)
    private String phoneNumber;

    @Column(name = "external_id", unique = true, nullable = false, length = 20)
    private String externalId;

    @Builder.Default
    @Column(name = "admin_rank", nullable = false)
    private Short adminRank = 0;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEventRegistration> userEventRegistration = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEventSubscription> userEventSubscriptions = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> userRoles = new ArrayList<>();
}
