package spring.boot.optic.okulist.dto.payment;

import lombok.Data;

@Data
public class LiqpayCreatePaymentRequestDto {
    private int version;
    private String publickey;
    private String action;
    private String amount;
    private String currency;
    private String description;
    private String orderid;
}
