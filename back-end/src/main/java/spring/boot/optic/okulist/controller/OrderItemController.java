package spring.boot.optic.okulist.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.optic.okulist.dto.order.orderitem.OrderItemDto;
import spring.boot.optic.okulist.service.order.orderitem.OrderItemService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/orders/items")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    @Operation(summary = "Get orderItem by id", description = "Get avalilable orderItem by id")
    public OrderItemDto getOrderItemById(@PathVariable Long id) {
        return orderItemService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{productId}")
    @Operation(summary = "Get orderItem by productId",
            description = "Get available orderItem by productId")
    public OrderItemDto getByProductId(@PathVariable Long productId) {
        return orderItemService.getByProductId(productId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete orderItem by id",
            description = "Soft delete of orderItem by id from orders")
    public void deleteById(@PathVariable Long id) {
        orderItemService.deleteById(id);
    }
}
