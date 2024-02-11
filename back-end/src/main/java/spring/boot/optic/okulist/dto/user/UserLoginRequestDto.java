package spring.boot.optic.okulist.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import spring.boot.optic.okulist.validation.user.email.EmailValidation;
import spring.boot.optic.okulist.validation.user.password.PasswordValidation;

@Data
public class UserLoginRequestDto {
    @EmailValidation
    private String email;
    @NotNull
    @PasswordValidation
    @Length(min = 4, max = 155)
    private String password;
}

