package nakup.payment.controller;

import nakup.payment.dto.PaymentDetailsRequest;
import nakup.payment.dto.PaymentGetRequest;
import nakup.payment.model.Payment;
import nakup.payment.repository.PaymentRepository;
import nakup.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping
    public Payment initiatePayment(@RequestBody PaymentDetailsRequest request) {
        return paymentService.pay(request);
    }

    @GetMapping("/all")
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping
    public Payment getPaymentById(@RequestBody PaymentGetRequest request) {
        return paymentRepository.findById(request.getPaymentId()).orElse(null);
    }

    @GetMapping("user")
    public Payment getPaymentsByUser(@RequestBody PaymentGetRequest request) {
        return paymentRepository.findByUserId(request.getUserId());
    }

    @GetMapping("order")
    public Payment getPaymentsByOrder(@RequestBody PaymentGetRequest request) {
        return paymentRepository.findByOrderId(request.getOrderId());
    }
}
