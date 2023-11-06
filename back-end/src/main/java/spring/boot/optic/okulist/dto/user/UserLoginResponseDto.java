package spring.boot.optic.okulist.dto.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserLoginResponseDto {
    private final String token;
}
