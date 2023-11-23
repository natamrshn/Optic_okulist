package spring.boot.optic.okulist.service.user;

import spring.boot.optic.okulist.dto.user.UserRegistrationRequestDto;
import spring.boot.optic.okulist.dto.user.UserResponseDto;
import spring.boot.optic.okulist.exception.RegistrationException;
import spring.boot.optic.okulist.model.User;

public interface UserService {
    UserResponseDto register(
            UserRegistrationRequestDto requestDto) throws RegistrationException;

    User getAuthenticated();
}
