package spring.boot.optic.okulist.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.optic.okulist.dto.order.CreateOrderRequestDto;
import spring.boot.optic.okulist.dto.order.OrderResponseDto;
import spring.boot.optic.okulist.dto.order.UpdateOrderRequestDto;
import spring.boot.optic.okulist.service.order.OrderService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    @Operation(summary = "Get all orders", description = "Get a list of all available orders")
    public List<OrderResponseDto> findAll(@ParameterObject Pageable pageable) {
        return orderService.getAll(pageable);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    @Operation(summary = "Get order by id", description = "Get available order by id")
    public OrderResponseDto getOrderById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{userId}")
    @Operation(summary = "Get order by userId", description = "Get available order by userId")
    public OrderResponseDto getByUserId(@PathVariable Long userId) {
        return orderService.getByUserId(userId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Update order by id", description = "Update available order by id")
    public OrderResponseDto update(@PathVariable Long id,
                           @RequestBody @Valid UpdateOrderRequestDto requestDto) {
        return orderService.update(id, requestDto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    @Operation(summary = "Add order to repository", description = "Add valid order to repository")
    public OrderResponseDto addOrder(@RequestBody @Valid CreateOrderRequestDto requestDto) {
        return orderService.addOrder(requestDto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}/items/{orderItemId}")
    @Operation(summary = "Get orderitem by order id and item id",
            description = "Get available orderitem by order id and item id")
    public OrderResponseDto getOrderItemByOrderIdAndItemId(
            @PathVariable Long orderId,
            @PathVariable Long orderItemId
    ) {
        return orderService.getByOrderIdAndOrderItemId(orderId, orderItemId);
    }
}
