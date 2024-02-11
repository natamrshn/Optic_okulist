package spring.boot.optic.okulist.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import spring.boot.optic.okulist.validation.user.email.EmailValidation;
import spring.boot.optic.okulist.validation.user.password.FieldMatch;
import spring.boot.optic.okulist.validation.user.password.PasswordValidation;

@Data
@FieldMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Password and repeat password shouldn't be empty and should be equal"
)
public class UserRegistrationRequestDto {
    @EmailValidation
    private String email;
    @NotNull
    @PasswordValidation
    @Size(min = 6, max = 100)
    private String password;
    @NotNull
    @PasswordValidation
    @Size(min = 6, max = 100)
    private String repeatPassword;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Long phoneNumber;
}

