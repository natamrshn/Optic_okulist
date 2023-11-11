package spring.boot.optic.okulist.dto.contactlenses;

import java.util.List;
import java.util.Set;
import lombok.Data;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;

@Data
public class ContactLensesResponseDto {
    private Long id;
    private String name;
    private double price;
    private String description;
    private String identifier;
    private Set<CategoryResponseDto> categories;
    private String diopter;
    private String cylinder;
    private String angle;
    private String baseCurve;
    private String lensColor;
    private String adidation;
    private List<String> availableDiopterOptions;
}
