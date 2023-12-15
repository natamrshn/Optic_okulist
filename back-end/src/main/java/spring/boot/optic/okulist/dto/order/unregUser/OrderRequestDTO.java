package spring.boot.optic.okulist.dto.order.unregUser;

import lombok.Data;
import spring.boot.optic.okulist.dto.order.orderitem.OrderItemDto;

import java.util.List;

@Data
public class OrderRequestDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String shippingAddress;
    private List<OrderItemDto> products;
}
