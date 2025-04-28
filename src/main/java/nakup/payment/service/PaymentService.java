package nakup.payment.service;

import jakarta.transaction.Transactional;
import nakup.payment.dto.PaymentDetailsRequest;
import nakup.payment.model.Payment;
import nakup.payment.model.event.PaymentCompletedEvent;
import nakup.payment.model.event.PaymentInitiatedEvent;
import nakup.payment.repository.PaymentRepository;
import nakup.payment.service.event.PaymentCompletedProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentCompletedProducer paymentCompletedProducer;

    @Transactional
    public Payment pay(PaymentDetailsRequest request) {
        Payment payment = new Payment();
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setOrderId(request.getOrderId());
        payment.setUserId(request.getUserId());
        payment.setInitiatedAt(LocalDateTime.now());
        if (request.getDeterminedSuccess() == null || request.getDeterminedSuccess()) {
            payment.setSuccess(Boolean.TRUE);
            payment.setCompletedAt(LocalDateTime.now());
        }
        else {
            payment.setSuccess(Boolean.FALSE);
        }

        paymentRepository.save(payment);

        return payment;
    }

    public void mockPayment(PaymentInitiatedEvent event) throws InterruptedException {
        PaymentDetailsRequest request = new PaymentDetailsRequest();

        request.setAmount(BigDecimal.valueOf(event.amount()));
        request.setCurrency("CZK");
        request.setOrderId(event.orderId());
        request.setUserId(event.userId());
        request.setDeterminedSuccess(new Random().nextBoolean());

        System.out.println("PENDING PAYMENT RNNNNNNN");

        Thread.sleep(3000);

        Payment payment = pay(request);

        if (payment.getSuccess()) {
            System.out.println("Payment successful, order-id: " + payment.getOrderId());
        }
        else {
            System.out.println("Payment failed, order-id: " + payment.getOrderId());
        }

        paymentCompletedProducer.publishPaymentCompletedEvent(new PaymentCompletedEvent(event.orderId(), payment.getSuccess()));
    }
}
