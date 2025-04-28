package nakup.payment.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.SecondaryRow;

public record PaymentInitiatedEvent(
        @JsonProperty("order-id")
        Long orderId,
        @JsonProperty("user-id")
        Long userId,
        double amount){
}
