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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.optic.okulist.dto.order.CreateOrderRequestDto;
import spring.boot.optic.okulist.dto.order.OrderResponseDto;
import spring.boot.optic.okulist.dto.order.UpdateOrderRequestDto;
import spring.boot.optic.okulist.model.Order;
import spring.boot.optic.okulist.model.RegisteredUser;
import spring.boot.optic.okulist.model.user.User;
import spring.boot.optic.okulist.service.order.OrderService;
import spring.boot.optic.okulist.service.user.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    //@PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    @Operation(summary = "Add order to repository", description = "Add valid order to repository")
    public OrderResponseDto addOrder(Authentication authentication,
                                     @RequestBody @Valid CreateOrderRequestDto requestDto) {
        if ( (authentication == null || !authentication.isAuthenticated())
                && requestDto.getSessionId() == null) { //TODO: validate other fields
            throw new RuntimeException("User should be authenticated or sessionId provided"); //TODO: proper exception type and handling
        }
        User user = userService.getUser(requestDto.getSessionId());
        return orderService.addOrder(user.getId(), requestDto);
    }

    //@PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    @Operation(summary = "Get all orders", description = "Get a list of all available orders")
    public List<OrderResponseDto> findAllUserOrders(@RequestParam(required = false) String sessionId,
                                                    Authentication authentication) {
        if ( (authentication == null || !authentication.isAuthenticated())
                && sessionId == null) {
            throw new RuntimeException("User should be authenticated or sessionId provided"); //TODO: proper exception type and handling
        }
        User user = userService.getUser(sessionId);
        return orderService.getByUserId(user.getId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get order by userId", description = "Get available order by userId")
    public List<OrderResponseDto> getByUserId(@PathVariable Long userId) {
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

    @GetMapping("/logs")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Order> getAllOrdersSortedByDateDesc() {
        return orderService.findAllOrdersSortedByDateDesc();
    }
}
