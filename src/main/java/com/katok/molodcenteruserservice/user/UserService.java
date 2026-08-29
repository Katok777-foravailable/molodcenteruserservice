package com.katok.molodcenteruserservice.user;

import com.katok.molodcenteruserservice.exception.ValueNotFound;
import com.katok.molodcenteruserservice.utils.NanoIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Retryable(value = DataIntegrityViolationException.class, maxRetries = 3)
    public User createUser(User user) {
        user.setExternalId(NanoIdGenerator.generate(20));
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ValueNotFound("Користувач з айді " + id + " не знайдено"));
    }

    public User getUserByTelegramUserId(Long telegramUserId) {
        return userRepository.findUserByTelegramUserId(telegramUserId)
                .orElseThrow(() -> new ValueNotFound("Користувач з телеграм айді " + telegramUserId + " не знайдений"));
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ValueNotFound("Користувач з номером телефону " + phoneNumber + " не знайдений"));
    }

    public User getUserByExternalId(String externalId) {
        return userRepository.findUserByExternalId(externalId)
                .orElseThrow(() -> new ValueNotFound("Користувач з external id " + externalId + " не знайдений"));
    }
}
