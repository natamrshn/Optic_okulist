package spring.boot.optic.okulist.service.user.passwordreset;

import spring.boot.optic.okulist.dto.user.UserPasswordUpdateRequestDto;
import spring.boot.optic.okulist.dto.user.UserResponseDto;

public interface UserPasswordUpdateService {
    UserResponseDto updatePassword(Long userId, UserPasswordUpdateRequestDto updateRequestDto);
    void verifyCodeAndChangePassword(Long userId, String verificationCode, String newPassword);
}
