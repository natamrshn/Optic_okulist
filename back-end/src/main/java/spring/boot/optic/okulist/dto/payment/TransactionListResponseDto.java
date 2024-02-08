package spring.boot.optic.okulist.dto.payment;

import java.util.List;
import lombok.Data;

@Data
public class TransactionListResponseDto {
    private String reason;
    private int reasonCode;
    private List<TransactionDto> transactionlList;
}
