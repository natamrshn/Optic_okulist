package spring.boot.optic.okulist.dto.contactlenses;

import lombok.Data;

@Data
public class ContactLensesRequestDto {
    private double diopter;
    private double cylinder;
    private double angle;
    private double baseCurve;
    private String lensColor;
    private String adidation;
}


