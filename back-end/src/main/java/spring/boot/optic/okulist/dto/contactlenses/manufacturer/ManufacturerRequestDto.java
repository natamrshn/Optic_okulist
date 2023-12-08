package spring.boot.optic.okulist.dto.contactlenses.manufacturer;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ManufacturerRequestDto {
    private String name;
    private Long colorId;
    private Long cylinderId;
    private Long degreeId;
    private Long diopterId;
    private Long sphereId;
    private String imageUrl;
    private String imageUrlSecond;
}
