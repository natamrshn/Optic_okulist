package spring.boot.optic.okulist.dto.liquid;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class LiquidRequestDto {
    @Positive
    private int volume;
}
