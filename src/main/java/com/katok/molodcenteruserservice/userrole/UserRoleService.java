package com.katok.molodcenteruserservice.userrole;

import com.katok.molodcenteruserservice.exception.ValueNotFound;
import com.katok.molodcenteruserservice.user.UserService;
import com.katok.molodcenteruserservice.youthcenter.YouthCenterClient;
import com.katok.molodcenteruserservice.youthcenter.YouthCenterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final YouthCenterClient youthCenterClient;
    private final UserService userService;

    public UserRole changeUserRole(UserRole userRole) {
        if (userService.getUserById(userRole.getUser().getId()) == null) {
            throw new IllegalArgumentException("Юзера з айді " + userRole.getUser().getId() + " не знайдено!");
        }

        ResponseEntity<YouthCenterDto> youthCenterDtoResponseEntity = youthCenterClient.getYouthCenterById(userRole.getYouthCenterId());

        if (youthCenterDtoResponseEntity.getStatusCode().is4xxClientError()) {
            throw new IllegalArgumentException("Молодіжного центра з айді " + userRole.getYouthCenterId() + " не знайдено!");
        }

        return userRoleRepository.save(userRole);
    }

    public Page<UserRole> getUserRolesByYouthCenterId(Long userId, Long youthCenterId, Pageable pageable) {
        return userRoleRepository.findUserRolesByUserIdAndYouthCenterId(userId, youthCenterId, pageable);
    }

    public Page<UserRole> getUserRolesByUserId(Long userId, Pageable pageable) {
        return userRoleRepository.findUserRolesByUserIdAndYouthCenterId(userId, null, pageable);
    }

    public Page<UserRole> getUserRoleByUserIdAndYouthCenter(Long userId, Long youthCenterId, Pageable pageable) {
        return userRoleRepository.findUserRolesByUserIdAndYouthCenterId(userId, youthCenterId, pageable);
    }

    public UserRole getUserRoleById(Long id) {
        return userRoleRepository.findById(id)
                .orElseThrow(() -> new ValueNotFound("Роль юзера з айді " + id + " не знайдено"));
    }
}
