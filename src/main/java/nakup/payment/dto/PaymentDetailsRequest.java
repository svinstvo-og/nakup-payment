package nakup.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentDetailsRequest {
    @JsonProperty("order-id")
    private Long orderId;
    @JsonProperty("user-id")
    private Long userId;
    private BigDecimal amount;
    private String currency;

    //Imitation of third-party payment API
    @JsonProperty("success")
    private Boolean determinedSuccess;
}
