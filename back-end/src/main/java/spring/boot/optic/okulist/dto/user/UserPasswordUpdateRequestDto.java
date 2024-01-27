package spring.boot.optic.okulist.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserPasswordUpdateRequestDto {
    private String password;
    @NotNull
    @Size(min = 6, max = 6)
    private String verificationCode;
}
