package spring.boot.optic.okulist.service.order;

import java.util.List;
import org.springframework.data.domain.Pageable;
import spring.boot.optic.okulist.dto.order.CreateOrderRequestDto;
import spring.boot.optic.okulist.dto.order.OrderResponseDto;
import spring.boot.optic.okulist.dto.order.UpdateOrderRequestDto;

public interface OrderService {
    OrderResponseDto update(Long id, UpdateOrderRequestDto requestDto);

    OrderResponseDto addOrder(CreateOrderRequestDto createOrderRequestDto);

    OrderResponseDto getByUserId(Long userId);

    OrderResponseDto findById(Long id);

    List<OrderResponseDto> getAll(Pageable pageable);

    OrderResponseDto getByOrderIdAndOrderItemId(Long orderId, Long orderItemsId);
}
