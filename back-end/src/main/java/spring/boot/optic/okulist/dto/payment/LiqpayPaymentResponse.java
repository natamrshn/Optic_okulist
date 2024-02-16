package spring.boot.optic.okulist.dto.payment;

import lombok.Data;

@Data
public class LiqpayPaymentResponse {
    private String status;
    private String transactionid;
    private String orderid;
    private String amount;
    private String currency;
    private String signature;
}
