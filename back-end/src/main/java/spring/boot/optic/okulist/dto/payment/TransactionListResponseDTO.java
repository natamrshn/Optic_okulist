package spring.boot.optic.okulist.dto.payment;

import lombok.Data;

import java.util.List;

@Data
public class TransactionListResponseDTO {
    private String reason;
    private int reasonCode;
    private List<TransactionDTO> transactionList;
}