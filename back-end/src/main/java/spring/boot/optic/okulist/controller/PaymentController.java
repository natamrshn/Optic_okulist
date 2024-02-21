package spring.boot.optic.okulist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    // private LiqpayServiceImpl liqpayService;

  /*  @PostMapping("/checkout")
    public String checkout(HttpServletRequest request) {
        // Створення запиту до Liqpay
        LiqpayCreatePaymentRequest requestDto = ...

        // Генерація даних та підписів
        String data = liqpayService.generateData(requestDto);
        String signature = liqpayService.generateSignature(data);

        // Створення HTML-форми
        String htmlForm = liqpayService.generateHtmlForm(data, signature);

        return htmlForm;
    }

    @PostMapping("/api/callback")
    public void callback(HttpServletRequest request) {
        // Отримання даних та підписів з запиту
        String data = request.getParameter("data");
        String signature = request.getParameter("signature");

        // Перевірка підпису
        boolean isValidSignature = liqpayService.verifySignature(data, signature);

        if (isValidSignature) {
            // Отримання статусу операції
            String status = liqpayService.getPaymentStatus(data);

            // Обробка статусу
            if (status.equals("success")) {
                // ...
            } else {
                // ...
            }
        } else {
            // ...
        }
    }

@PostMapping("/checkout")
public String checkout(HttpServletRequest request) {
    String data = liqpayService.initiatePayment(...); // Отримати дані з LiqpayService
    return "redirect:" + liqpayCheckoutUrl + "?" + data;
}

@PostMapping("/callback")
public void callback(HttpServletRequest request) {
    liqpayService.processCallback(request);
}


   */
}
