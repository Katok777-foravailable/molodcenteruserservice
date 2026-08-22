package com.katok.molodcenteruserservice.usereventregistration;

import com.katok.molodcenteruserservice.event.EventClient;
import com.katok.molodcenteruserservice.event.EventDto;
import com.katok.molodcenteruserservice.exception.ValueNotFound;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventRegistrationService {
    private final UserEventRegistrationRepository userEventRegistrationRepository;
    private final EventClient eventClient;

    public Page<UserEventRegistration> getUsersEventRegistration(Pageable pageable) {
        return userEventRegistrationRepository.findAll(pageable);
    }

    public Page<UserEventRegistration> getUsersEventRegistrationByEventIdAndUserId(@Nullable Long eventId, @Nullable Long userId, Pageable pageable) {
        return userEventRegistrationRepository.findUserEventRegistrationByEventIdAndUserId(eventId, userId, pageable);
    }

    public UserEventRegistration registerUser(UserEventRegistration userEventRegistration) {
        ResponseEntity<EventDto> event = eventClient.getEventById(userEventRegistration.getEventId());
        if (event.getStatusCode().is4xxClientError() || !event.hasBody()) {
            throw new ValueNotFound("Події з айді " + userEventRegistration.getEventId() + " не знайдено!");
        }

        return userEventRegistrationRepository.save(userEventRegistration);
    }

    public UserEventRegistration getUserRegistrationById(Long id) {
        return userEventRegistrationRepository.findById(id)
                .orElseThrow(() -> new ValueNotFound("Реєстрацію юзера з айді " + id + " не знайдено"));
    }
}
