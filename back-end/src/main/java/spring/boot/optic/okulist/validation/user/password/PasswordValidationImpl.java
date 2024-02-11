package spring.boot.optic.okulist.validation.user.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class PasswordValidationImpl implements ConstraintValidator<PasswordValidation,String> {
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
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
