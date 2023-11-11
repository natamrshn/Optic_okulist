package spring.boot.optic.okulist.dto.contactlenses;

import lombok.Data;

@Data
public class ContactLensesRequestDto {
    private String diopter;
    private String cylinder;
    private String angle;
    private String baseCurve;
    private String lensColor;
    private String adidation;
}


