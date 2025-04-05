package nakup.payment.repository;

import nakup.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    public Payment findByUserId(Long userId);

    public Payment findByOrderId(Long orderId);
}
