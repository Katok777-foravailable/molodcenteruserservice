package com.katok.molodcenteruserservice.user;

import com.katok.molodcenteruserservice.exception.ValueNotFound;
import com.katok.molodcenteruserservice.utils.NanoIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    public final static Set<Character> accessChars = Collections.unmodifiableSet(new HashSet<>() {{
        for (char letter : "АаБбВвГгҐґДдЕеЄєЖжЗзИиІіЇїЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЬьЮюЯя'".toCharArray()) {
            add(letter);
        }
    }});

    private final UserRepository userRepository;

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Retryable(value = DataIntegrityViolationException.class, maxRetries = 3)
    public User createUser(User user) {
        user.setExternalId(NanoIdGenerator.generate(20));

        if (user.getName().length() > 30) {
            throw new IllegalArgumentException("Ім'я не може бути більше 30 символів!");
        }
        if (user.getLastName().length() > 30) {
            throw new IllegalArgumentException("Фамілія не може бути більше 30 символів!");
        }

        for (char letter : user.getName().toCharArray()) {
            if (accessChars.contains(letter)) {
                continue;
            }

            throw new IllegalArgumentException("Ім'я може складатися лише з українських букв і апострофа!");
        }
        for (char letter : user.getLastName().toCharArray()) {
            if (accessChars.contains(letter)) {
                continue;
            }

            throw new IllegalArgumentException("Фамілія може складатися лише з українських букв і апострофа!");
        }

        user.setName(String.valueOf(user.getName().charAt(0)).toUpperCase() + user.getName().substring(1).toLowerCase());
        user.setLastName(String.valueOf(user.getLastName().charAt(0)).toUpperCase() + user.getLastName().substring(1).toLowerCase());

        return userRepository.save(user);
    }

    public User updateUser(User user) {
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

    public Page<User> getUsersByAdminRank(short adminRank, Pageable pageable) {
        return userRepository.findUsersByAdminRank(adminRank, pageable);
    }
}
