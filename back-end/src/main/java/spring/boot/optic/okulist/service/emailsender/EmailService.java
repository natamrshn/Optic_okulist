package spring.boot.optic.okulist.service.emailsender;

import spring.boot.optic.okulist.model.Order;

public interface EmailService {
    void sendStatusChangeEmail(String userEmail, Order.Status newStatus);
}
