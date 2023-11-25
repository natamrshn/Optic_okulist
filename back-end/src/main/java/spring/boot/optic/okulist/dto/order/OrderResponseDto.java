package spring.boot.optic.okulist.dto.order;

import spring.boot.optic.okulist.dto.order.orderitem.OrderItemDto;
import spring.boot.optic.okulist.model.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class OrderResponseDto {
    private Long id;
    private Long userId;
    private Set<OrderItemDto> orderItems;
    private LocalDate orderDate;
    private BigDecimal total;
    private Order.Status status;
}
