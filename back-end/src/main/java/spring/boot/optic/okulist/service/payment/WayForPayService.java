package spring.boot.optic.okulist.service.payment;

import spring.boot.optic.okulist.dto.payment.WayForPayRequestDto;
import spring.boot.optic.okulist.dto.payment.WayForPayResponseDto;

public interface WayForPayService {
    WayForPayResponseDto pay(WayForPayRequestDto dto);
}
