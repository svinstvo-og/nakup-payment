package nakup.payment.service.event;

import nakup.payment.model.event.PaymentCompletedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentCompletedProducer {
    private static final String TOPIC = "payment-completed";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentCompletedProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishPaymentCompletedEvent(PaymentCompletedEvent event) {
        System.out.println("Sending payment-completed event: " + event);
        kafkaTemplate.send(TOPIC, event);
    }
}
