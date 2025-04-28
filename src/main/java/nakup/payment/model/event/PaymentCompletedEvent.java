package nakup.payment.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaymentCompletedEvent(
        @JsonProperty("order-id")
        Long orderId,
        boolean success
) {
}
