package spring.boot.optic.okulist.service.user.impl;

import java.util.HashSet;
import java.util.Optional;
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
import spring.boot.optic.okulist.model.RegisteredUser;
import spring.boot.optic.okulist.model.Role;
import spring.boot.optic.okulist.model.user.TemporaryUser;
import spring.boot.optic.okulist.model.user.User;
import spring.boot.optic.okulist.repository.RoleRepository;
import spring.boot.optic.okulist.repository.TemporaryUserRepository;
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
    private final TemporaryUserRepository temporaryUserRepository;

    public boolean isFirstUser() {
        return userRepository.count() == 0;
    }

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration");
        }
        RegisteredUser user = new RegisteredUser();
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
        RegisteredUser user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with id: " + userId));
        if (updateRequestDto.getFirstName() != null) {
            user.setFirstName(updateRequestDto.getFirstName());
        }
        if (updateRequestDto.getLastName() != null) {
            user.setLastName(updateRequestDto.getLastName());
        }
        if (updateRequestDto.getPhoneNumber() != null) {
            user.setPhoneNumber(updateRequestDto.getPhoneNumber());
        }
        RegisteredUser updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public Optional<RegisteredUser> getAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName());
    }

    @Override
    public Optional<TemporaryUser> getBySessionId(String sessionId) {
        return temporaryUserRepository.findBySessionId(sessionId);
    }

    @Override
    public TemporaryUser createTemporary(String sessionId) {
        TemporaryUser temporaryUser = new TemporaryUser();
        temporaryUser.setSessionId(sessionId);
        return temporaryUserRepository.save(temporaryUser);
    }

    @Override
    public User getUserOrCreateNew(String sessionId) {
        User user;
        Optional<RegisteredUser> registeredUser = getAuthenticated();
        if (registeredUser.isPresent()) {
            user = registeredUser.get();
        } else {
            Optional<TemporaryUser> temporaryUser = getBySessionId(sessionId);
            user = temporaryUser.orElseGet(() -> createTemporary(sessionId));
        }
        return user;
    }

    @Override
    public User getUser(String sessionId) {
        User user;
        Optional<RegisteredUser> registeredUser = getAuthenticated();
        if (registeredUser.isPresent()) {
            user = registeredUser.get();
        } else {
            Optional<TemporaryUser> temporaryUser = getBySessionId(sessionId);
            user = temporaryUser
                    .orElseThrow(() ->
                            new EntityNotFoundException("No temporary user found for session ID: " + sessionId));
        }
        return user;
    }

    /*
     private final AuthenticationService authenticationService;

    private String resolveSessionId(String sessionIdFromCookie) {
        if (sessionIdFromCookie != null) {
            return sessionIdFromCookie;
        }

        // Якщо cookie sessionId відсутня, можливо користувач аутентифікований
        // Спробуйте отримати sessionId з аутентифікаційного токену
        String sessionIdFromToken = authenticationService.extractSessionIdFromToken();
        if (sessionIdFromToken != null) {
            return sessionIdFromToken;
        }

        // Якщо немає куки та аутентифікації, створіть новий sessionId
        String newSessionId = userService.createTemporarySession();
        Cookie sessionIdCookie = new Cookie("sessionId", newSessionId);
        sessionIdCookie.setMaxAge(-1);  // Закриття браузера
        sessionIdCookie.setPath("/");
        response.addCookie(sessionIdCookie);
        return newSessionId;
    }
 */
}
