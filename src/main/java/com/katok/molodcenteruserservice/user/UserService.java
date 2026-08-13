package com.katok.molodcenteruserservice.user;

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
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " undefined"));
    }

    public User getUserByTelegramUserId(Long telegramUserId) {
        return userRepository.findUserByTelegramUserId(telegramUserId)
                .orElseThrow(() -> new IllegalArgumentException("User with telegram user id " + telegramUserId + " undefined"));
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("User with phone number " + phoneNumber + " undefined"));
    }
}
