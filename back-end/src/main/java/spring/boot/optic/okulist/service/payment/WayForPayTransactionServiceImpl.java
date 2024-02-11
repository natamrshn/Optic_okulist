package spring.boot.optic.okulist.service.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spring.boot.optic.okulist.dto.payment.TransactionListRequestDto;
import spring.boot.optic.okulist.dto.payment.TransactionListResponseDto;

@Service
@RequiredArgsConstructor
public class WayForPayTransactionServiceImpl implements WayForPayTransactionService {
    private static final String WAYFORPAY_API_URL = "https://api.wayforpay.com/api";

    private final RestTemplate restTemplate;

    @Override
    public TransactionListResponseDto getTransactionList(TransactionListRequestDto requestDto) {
        String url = WAYFORPAY_API_URL + "/transactionList";
        return restTemplate.postForObject(url, requestDto, TransactionListResponseDto.class);
    }
}

