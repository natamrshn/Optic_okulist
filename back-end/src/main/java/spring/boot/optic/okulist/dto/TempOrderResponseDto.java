package spring.boot.optic.okulist.dto;

import lombok.Data;

@Data
public class TempOrderResponseDto {
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String shoppingAddress;
}
