package spring.boot.optic.okulist.service.user.passwordreset;

public interface UserPasswordInitiationService {

    void initiatePasswordChange();

    String generateVerificationCode();
}
