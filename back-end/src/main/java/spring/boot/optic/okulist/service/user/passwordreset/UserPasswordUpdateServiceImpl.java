package spring.boot.optic.okulist.service.user.passwordreset;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.user.UserPasswordUpdateRequestDto;
import spring.boot.optic.okulist.dto.user.UserResponseDto;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.exception.VerificationCodeMismatchException;
import spring.boot.optic.okulist.mapper.UserMapper;
import spring.boot.optic.okulist.model.User;
import spring.boot.optic.okulist.repository.UserRepository;
import spring.boot.optic.okulist.service.emailsender.EmailService;

@Service
@RequiredArgsConstructor
@EnableCaching
public class UserPasswordUpdateServiceImpl implements UserPasswordUpdateService {
    private final PasswordEncoder passwordEncoder;
    private final CacheManager cacheManager;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    public UserResponseDto updatePassword(Long userId,
                                          UserPasswordUpdateRequestDto updateRequestDto) {
        verifyCodeAndChangePassword(userId, updateRequestDto.getVerificationCode(),
                updateRequestDto.getPassword());
        User user = getUserById(userId);
        UserResponseDto responseDto = userMapper.toDto(user);

        // Send notification email
        String userEmail = user.getEmail();
        emailService.sendPasswordChangeConfirmation(userEmail);

        return responseDto;
    }

    @Override
    public void verifyCodeAndChangePassword(Long userId, String verificationCode,
                                            String newPassword) {
        String storedCode = getVerificationCode(userId);
        if (storedCode != null && storedCode.equals(verificationCode)) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() ->
                            new EntityNotFoundException("User not found with id: " + userId));
            user.setPassword(passwordEncoder.encode(newPassword));
            clearVerificationCode(userId);
            userRepository.save(user);
        } else {
            throw new VerificationCodeMismatchException("Invalid verification code");
        }
    }

    private String getVerificationCode(Long userId) {
        Cache cache = cacheManager.getCache("verificationCodes");
        return (String) cache.get(userId, String.class);
    }

    private void clearVerificationCode(Long userId) {
        Cache cache = cacheManager.getCache("verificationCodes");
        cache.evict(userId);
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with id: " + userId));
    }
}
