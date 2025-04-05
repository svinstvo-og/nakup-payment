package nakup.payment.service;

import jakarta.transaction.Transactional;
import nakup.payment.dto.PaymentDetailsRequest;
import nakup.payment.model.Payment;
import nakup.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

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
}
