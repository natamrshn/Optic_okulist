package spring.boot.optic.okulist.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderRequestDto {
    @NotNull
    private String shippingAddress;
    private String sessionId;
    private Long phoneNumber;
    private String firstName;
    private String lastName;
}
