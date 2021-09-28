package br.com.aaribeiro.icarros.dto.questions;

import lombok.*;
import java.math.BigDecimal;

@With
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DealResponseDTO {
    private String orderId;
    private BigDecimal totalPrice;
}
