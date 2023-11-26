package spring.boot.optic.okulist.dto.order.orderitem;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long id;
    private Long productId;
    private int quantity;
}
