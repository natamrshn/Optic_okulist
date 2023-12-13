package spring.boot.optic.okulist.service.order;

import java.util.List;
import org.springframework.data.domain.Pageable;
import spring.boot.optic.okulist.dto.order.CreateOrderRequestDto;
import spring.boot.optic.okulist.dto.order.CreateOrderRequestDtoNonRegUser;
import spring.boot.optic.okulist.dto.order.OrderResponseDto;
import spring.boot.optic.okulist.dto.order.UpdateOrderRequestDto;
import spring.boot.optic.okulist.model.Order;

public interface OrderService {
    OrderResponseDto update(Long id, UpdateOrderRequestDto requestDto);

    OrderResponseDto addOrder(Long id,CreateOrderRequestDto createOrderRequestDto);
    OrderResponseDto placeOrder(CreateOrderRequestDtoNonRegUser requestDto);

    OrderResponseDto getByUserId(Long userId);

    OrderResponseDto findById(Long id);

    List<OrderResponseDto> findAllOrders(Long id, Pageable pageable);

    OrderResponseDto getByOrderIdAndOrderItemId(Long orderId, Long orderItemsId);

    List<OrderResponseDto> findAllByUserEmail(String userEmail);

    OrderResponseDto updateOrderStatus(Long orderId, Order.Status status);
}
