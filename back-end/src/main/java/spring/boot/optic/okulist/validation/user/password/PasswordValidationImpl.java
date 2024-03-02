package spring.boot.optic.okulist.validation.user.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PasswordValidationImpl implements ConstraintValidator<PasswordValidation,String> {
    private static final String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=\\\\-_\\\\.,!?]).{8,}$";
    private static final Logger logger = LogManager.getLogger(PasswordValidationImpl.class);

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
        if (!isValid) {
            logger.warn("Invalid password format: {}", password);
        }
        return isValid;
    }
}
