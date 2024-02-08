package spring.boot.optic.okulist.dto.payment;

import lombok.Data;

@Data
public class TransactionDTO {
    private String transactionType;
    private String orderReference;
    private String createdDate;
    private String amount;
    private String currency;
    private String transactionStatus;
    private String processingDate;
    private String reasonCode;
    private String reason;
    private String email;
    private String phone;
    private String paymentSystem;
    private String cardPan;
    private String cardType;
    private String issuerBankCountry;
    private String issuerBankName;
    private String fee;
}