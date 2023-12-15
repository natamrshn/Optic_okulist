package spring.boot.optic.okulist.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
import spring.boot.optic.okulist.model.Order;
import spring.boot.optic.okulist.model.User;
import spring.boot.optic.okulist.service.order.OrderService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    @Operation(summary = "Add order to repository", description = "Add valid order to repository")
    public OrderResponseDto addOrder(Authentication authentication,
                                     @RequestBody @Valid CreateOrderRequestDto requestDto) {
        User user = (User) authentication.getPrincipal();
        return orderService.addOrder(user.getId(),requestDto);
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    @Operation(summary = "Get all orders", description = "Get a list of all available orders")
    public List<OrderResponseDto> findAllUserOrders(Authentication authentication) {
        String currentPrincipalName = authentication.getName();
        return orderService.findAllByUserEmail(currentPrincipalName);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("user/{userId}")
    @Operation(summary = "Get order by userId", description = "Get available order by userId")
    public OrderResponseDto getByUserId(@PathVariable Long userId) {
        return orderService.getByUserId(userId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Update order by id", description = "Update available order by id")
    public OrderResponseDto update(@PathVariable Long id,
                           @RequestBody @Valid UpdateOrderRequestDto requestDto) {
        return orderService.updateOrderStatus(id, Order.Status
                .valueOf(String.valueOf(requestDto.getStatus())));
    }
}

// search for admin