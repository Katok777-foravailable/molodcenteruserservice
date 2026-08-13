package com.katok.molodcenteruserservice.usereventregistration;

import com.katok.molodcenteruserservice.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_event_registrations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEventRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "event_id", nullable = false)
    private Long eventId;
}
