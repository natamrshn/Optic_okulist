package spring.boot.optic.okulist.dto.contactlenses;

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
    private double diopter;
    private double cylinder;
    private double angle;
    private double baseCurve;
    private String lensColor;
    private String adidation;
}
