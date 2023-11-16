package spring.boot.optic.okulist.dto.contactlenses.manufacturer;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ManufacturerResponseDto {
    private Long id;
    private String name;
    private Long colorId;
    private Long materialId;
    private Long cylinderId;
    private Long degreeId;
    private Long diopterId;
    private Long sphereId;
}
