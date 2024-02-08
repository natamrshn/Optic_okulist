package spring.boot.optic.okulist.service;

import spring.boot.optic.okulist.dto.payment.TransactionListRequestDTO;
import spring.boot.optic.okulist.dto.payment.TransactionListResponseDTO;

public interface WayForPayTransactionService {
    TransactionListResponseDTO getTransactionList(TransactionListRequestDTO requestDTO);
}
