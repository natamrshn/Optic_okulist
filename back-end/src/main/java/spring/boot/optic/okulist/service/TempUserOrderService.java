package spring.boot.optic.okulist.service;

import spring.boot.optic.okulist.dto.TempOrderRequestDto;
import spring.boot.optic.okulist.dto.TempOrderResponseDto;

public interface TempUserOrderService {
    TempOrderResponseDto processOrder(TempOrderRequestDto orderRequest);
}
