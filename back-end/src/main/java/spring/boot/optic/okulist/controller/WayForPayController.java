package spring.boot.optic.okulist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.optic.okulist.dto.payment.TransactionListRequestDto;
import spring.boot.optic.okulist.dto.payment.TransactionListResponseDto;
import spring.boot.optic.okulist.dto.payment.WayForPayRequestDto;
import spring.boot.optic.okulist.dto.payment.WayForPayResponseDto;
import spring.boot.optic.okulist.service.WayForPayService;
import spring.boot.optic.okulist.service.WayForPayTransactionService;

@RestController
@RequestMapping("/wayforpay")
@RequiredArgsConstructor
public class WayForPayController {
    private final WayForPayService wayForPayService;
    private final WayForPayTransactionService transactionService;

    @PostMapping("/pay")
    public ResponseEntity<WayForPayResponseDto> pay(@RequestBody WayForPayRequestDto dto) {
        try {
            WayForPayResponseDto response = wayForPayService.pay(dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new WayForPayResponseDto(false, e.getMessage(), null));
        }
    }

    @GetMapping("/callback")
    public String callback(@RequestParam String orderReference, @RequestParam String status) {
        // handle callback from WayForPay
        return "success";
    }

    @PostMapping("/transactions")
    public TransactionListResponseDto getTransactions(@RequestBody TransactionListRequestDto requestDto) {
        return transactionService.getTransactionList(requestDto);
    }
}
