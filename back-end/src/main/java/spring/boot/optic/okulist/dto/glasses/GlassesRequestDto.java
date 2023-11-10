package spring.boot.optic.okulist.dto.glasses;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GlassesRequestDto {
    private String color;
    @NotNull
    private String model;
    private String manufacturer;
}
