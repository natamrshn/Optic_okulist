package spring.boot.optic.okulist.dto.payment;

import lombok.Data;

@Data
public class WayForPayRequestDto {
    private String merchantAccount;
    private String orderReference;
    private String amount;
    private String currency;
    private String cardNumber;
    private String cardExpDate;
    private String cardCvv;
    private String cardHolderName;
    private String language;
    private String returnUrl;
}
