package spring.boot.optic.okulist.dto.order.unregser;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderRequestDtoNonRegUser {
    private String email;
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    @NotNull
    private String shippingAddress;
    private String address;
}
