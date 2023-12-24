package spring.boot.optic.okulist.service.user;

import java.util.Optional;
import spring.boot.optic.okulist.dto.user.UserRegistrationRequestDto;
import spring.boot.optic.okulist.dto.user.UserResponseDto;
import spring.boot.optic.okulist.dto.user.UserUpdateRequestDto;
import spring.boot.optic.okulist.exception.RegistrationException;
import spring.boot.optic.okulist.model.RegisteredUser;
import spring.boot.optic.okulist.model.user.TemporaryUser;
import spring.boot.optic.okulist.model.user.User;

public interface UserService {
    UserResponseDto register(
            UserRegistrationRequestDto requestDto) throws RegistrationException;

    UserResponseDto update(Long userId, UserUpdateRequestDto updateRequestDto);

    Optional<RegisteredUser> getAuthenticated();

    Optional<TemporaryUser> getBySessionId(String sessionId);

    TemporaryUser createTemporary(String sessionId);

    User getUserOrCreateNew(String sessionId);

    User getUser(String sessionId);
}
