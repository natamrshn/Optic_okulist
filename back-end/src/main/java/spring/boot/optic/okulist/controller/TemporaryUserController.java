package spring.boot.optic.okulist.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.optic.okulist.dto.TempOrderRequestDto;
import spring.boot.optic.okulist.dto.TempOrderResponseDto;
import spring.boot.optic.okulist.service.TempUserOrderService;

@Tag(name = "Temporary User Controller",
        description = "Endpoints for adding orders for temp user")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/temp-user-add-order")
public class TemporaryUserController {
    private final TempUserOrderService tempUserOrderService;

    @PostMapping
    public TempOrderResponseDto processOrder(@RequestBody TempOrderRequestDto orderRequest) {
        return tempUserOrderService.processOrder(orderRequest);
    }
}
