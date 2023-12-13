package spring.boot.optic.okulist.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderRequestDtoNonRegUser {
    private final String email;
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    @NotNull
    private String shippingAddress;
}
