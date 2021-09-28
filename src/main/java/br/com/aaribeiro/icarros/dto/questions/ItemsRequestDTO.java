package br.com.aaribeiro.icarros.dto.questions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemsRequestDTO {
    @NotNull(message = "Filed name can not be empty or null")
    private String name;

    @NotNull(message = "Filed value can not be empty or null")
    private BigDecimal value;
}
