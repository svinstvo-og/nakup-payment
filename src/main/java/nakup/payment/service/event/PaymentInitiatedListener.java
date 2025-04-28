package nakup.payment.service.event;

import nakup.payment.model.event.PaymentInitiatedEvent;
import nakup.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentInitiatedListener {

    @Autowired
    PaymentService paymentService;

    @KafkaListener(topics = "payment-initiated", groupId = "payment-service", properties = {"spring.json.value.default.type=nakup.payment.model.event.PaymentInitiatedEvent"})
    public void handlePaymentInitialized(PaymentInitiatedEvent event) throws InterruptedException {
        System.out.println("Accepted an 'items reserved' event: " + event);
        paymentService.mockPayment(event);
    }
}
