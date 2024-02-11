package spring.boot.optic.okulist.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import spring.boot.optic.okulist.validation.user.password.PasswordValidation;

@Data
public class UserPasswordUpdateRequestDto {
    @PasswordValidation
    @Size(min = 6, max = 100)
    private String password;
    @NotNull
    @Size(min = 6, max = 6)
    private String verificationCode;
}
