package nakup.payment.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentGetRequest {
    @JsonProperty("order-id")
    private Long orderId;
    @JsonProperty("payment-id")
    private Long paymentId;
    @JsonProperty("user-id")
    private Long userId;
}
