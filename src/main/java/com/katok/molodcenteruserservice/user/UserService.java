package com.katok.molodcenteruserservice.user;

import com.katok.molodcenteruserservice.exception.ValueNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User createUser(User user) {
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
}
