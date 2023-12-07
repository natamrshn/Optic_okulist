package spring.boot.optic.okulist.service.user.impl;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import spring.boot.optic.okulist.dto.user.UserRegistrationRequestDto;
import spring.boot.optic.okulist.dto.user.UserResponseDto;
import spring.boot.optic.okulist.dto.user.UserUpdateRequestDto;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.exception.RegistrationException;
import spring.boot.optic.okulist.mapper.UserMapper;
import spring.boot.optic.okulist.model.Role;
import spring.boot.optic.okulist.model.User;
import spring.boot.optic.okulist.repository.RoleRepository;
import spring.boot.optic.okulist.repository.UserRepository;
import spring.boot.optic.okulist.service.user.UserService;

@RequiredArgsConstructor
@Component
@Transactional
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    public boolean isFirstUser() {
        return userRepository.count() == 0;
    }

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration");
        }
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setPhoneNumber(requestDto.getPhoneNumber());
        if (isFirstUser()) {
            // Перший користувач отримує адміністраторські права
            Role mainAdminRole = roleRepository.findRoleByName(Role.RoleName.MAIN_ADMIN)
                    .orElseThrow(() -> new RegistrationException("Can't find role by name"));
            Set<Role> mainAdminRoleSet = new HashSet<>();
            mainAdminRoleSet.add(mainAdminRole);
            user.setRoles(mainAdminRoleSet);
            user.setCreatePermission(true);
            user.setUpdatePermission(true);
            user.setDeletePermission(true);
        } else if (user.getEmail().equals("admin2@example.com")) {
            // Другий користувач завжди отримує адміністраторські права
            Role adminRole = roleRepository.findRoleByName(Role.RoleName.ADMIN)
                    .orElseThrow(() -> new RegistrationException("Can't find role by name"));
            Set<Role> adminRoleSet = new HashSet<>();
            adminRoleSet.add(adminRole);
            user.setRoles(adminRoleSet);
        } else {
            // Інші користувачі отримують звичайні права користувача
            Role userRole = roleRepository.findRoleByName(Role.RoleName.USER)
                    .orElseThrow(() -> new RegistrationException("Can't find role by name"));
            Set<Role> defaultUserRoleSet = new HashSet<>();
            defaultUserRoleSet.add(userRole);
            user.setRoles(defaultUserRoleSet);
        }
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto update(Long userId, UserUpdateRequestDto updateRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        if (updateRequestDto.getFirstName() != null) {
            user.setFirstName(updateRequestDto.getFirstName());
        }
        if (updateRequestDto.getLastName() != null) {
            user.setLastName(updateRequestDto.getLastName());
        }
        if (updateRequestDto.getPhoneNumber() != null) {
            user.setPhoneNumber(updateRequestDto.getPhoneNumber());
        }
        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }


    @Override
    public User getAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName()).orElseThrow(
                () -> new EntityNotFoundException("Can`t find user with according email"
                        + authentication.getName()));
    }
}
