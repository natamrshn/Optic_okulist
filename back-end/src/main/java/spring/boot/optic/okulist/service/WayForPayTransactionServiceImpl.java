package spring.boot.optic.okulist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spring.boot.optic.okulist.dto.payment.TransactionListRequestDTO;
import spring.boot.optic.okulist.dto.payment.TransactionListResponseDTO;

@Service
@RequiredArgsConstructor
public class WayForPayTransactionServiceImpl implements WayForPayTransactionService {
    private final String WAYFORPAY_API_URL = "https://api.wayforpay.com/api";

    private final RestTemplate restTemplate;

    @Override
    public TransactionListResponseDTO getTransactionList(TransactionListRequestDTO requestDTO) {
        String url = WAYFORPAY_API_URL + "/transactionList";
        return restTemplate.postForObject(url, requestDTO, TransactionListResponseDTO.class);
    }
}

