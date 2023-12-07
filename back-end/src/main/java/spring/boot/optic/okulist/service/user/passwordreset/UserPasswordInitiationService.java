package spring.boot.optic.okulist.service.user.passwordreset;

import spring.boot.optic.okulist.model.User;

public interface UserPasswordInitiationService {

    void initiatePasswordChange(Long userId);

    User getUserById(Long userId);

    String getUserEmail(Long userId);

    String generateVerificationCode();
}
