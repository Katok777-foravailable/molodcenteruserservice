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

    @PostMapping
    public ResponseEntity<UserRoleDto> createUserRole(@RequestBody @Valid UserRoleDtoCreate userRoleDtoCreate) {
        User user = userService.getUserById(userRoleDtoCreate.getUserId());

        if (user == null) {
            throw new IllegalArgumentException("Юзера з айді " + userRoleDtoCreate.getUserId() + " не знайдено!");
        }

        UserRole userRole = new UserRole(
                null,
                userRoleDtoCreate.getYouthCenterId(),
                user,
                userRoleDtoCreate.getRole()
        );

        return ResponseEntity.ok(UserRoleDto.toUserRoleDto(userRoleService.createUserRole(userRole)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleDto> getUserRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(UserRoleDto.toUserRoleDto(userRoleService.getUserRoleById(id)));
    }

    @GetMapping
    public ResponseEntity<Page<UserRoleDto>> getUserRoleByYouthCenterId(@RequestParam Long youthCenterId,
                                                                        @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);

        return ResponseEntity.ok(userRoleService.getUserRolesByYouthCenterId(youthCenterId, pageable).map(UserRoleDto::toUserRoleDto));
    }
}
