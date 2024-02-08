package spring.boot.optic.okulist.dto.payment;

import lombok.Data;

@Data
public class WayForPayResponseDto {
    private boolean success;
    private String message;
    private String transactionId;

    // Перевантажений конструктор
    public WayForPayResponseDto(boolean success, String message, String transactionId) {
        this.success = success;
        this.message = message;
        this.transactionId = transactionId;
    }

    // Конструктор зі значеннями за замовчуванням
    public WayForPayResponseDto(boolean success, String message) {
        this(success, message, null); // Встановлюємо transactionId на null
    }
}