package spring.boot.optic.okulist.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import spring.boot.optic.okulist.dto.product.ProductRequestDto;

@Data
public class TempOrderRequestDto {
    @NotNull
    private String email;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Long phoneNumber;
    @NotNull
    private String shoppingAddress;
    private List<ProductRequestDto> products;
    private int quantity;
    private BigDecimal total;
}
