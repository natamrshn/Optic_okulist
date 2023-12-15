package spring.boot.optic.okulist.dto.order.unregser;

import java.util.List;
import lombok.Data;
import spring.boot.optic.okulist.dto.order.orderitem.OrderItemDto;

@Data
public class OrderRequestDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String shippingAddress;
    private List<OrderItemDto> products;
}
