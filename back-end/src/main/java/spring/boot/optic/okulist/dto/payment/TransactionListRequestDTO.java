package spring.boot.optic.okulist.dto.payment;

import lombok.Data;

@Data
public class TransactionListRequestDTO {
    private String transactionType;
    private String merchantAccount;
    private String merchantSignature;
    private int apiVersion;
    private long dateBegin;
    private long dateEnd;
}