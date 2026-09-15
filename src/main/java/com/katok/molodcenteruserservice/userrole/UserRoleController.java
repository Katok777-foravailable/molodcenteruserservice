package com.katok.molodcenteruserservice.userrole;

import com.katok.molodcenteruserservice.user.User;
import com.katok.molodcenteruserservice.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/roles")
public class UserRoleController {
    private final UserRoleService userRoleService;
    private final UserService userService;

    @PutMapping
    public ResponseEntity<UserRoleDto> changeUserRole(@RequestBody @Valid UserRoleDtoCreate userRoleDtoCreate) {
        UserRole userRole;

        Page<UserRole> userRolePage = userRoleService.getUserRoleByUserIdAndYouthCenter(userRoleDtoCreate.getUserId(), userRoleDtoCreate.getYouthCenterId(), PageRequest.of(0, 10));
        if (!userRolePage.getContent().isEmpty()) {
            userRole = userRolePage.getContent().getFirst();
            userRole.setRole(userRoleDtoCreate.getRole());
        } else {
            User user = userService.getUserById(userRoleDtoCreate.getUserId());

            if (user == null) {
                throw new IllegalArgumentException("Юзера з айді " + userRoleDtoCreate.getUserId() + " не знайдено!");
            }

            userRole = new UserRole(
                    null,
                    userRoleDtoCreate.getYouthCenterId(),
                    user,
                    userRoleDtoCreate.getRole()
            );
        }
        return ResponseEntity.ok(UserRoleDto.toUserRoleDto(userRoleService.changeUserRole(userRole)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleDto> getUserRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(UserRoleDto.toUserRoleDto(userRoleService.getUserRoleById(id)));
    }

    @GetMapping
    public ResponseEntity<Page<UserRoleDto>> getUserRoleByYouthCenterIdAndUserId(@RequestParam(required = false) Long userId,
                                                                        @RequestParam(required = false) Long youthCenterId,
                                                                        @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);

        return ResponseEntity.ok(userRoleService.getUserRolesByYouthCenterId(userId, youthCenterId, pageable).map(UserRoleDto::toUserRoleDto));
    }
}
