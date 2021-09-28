package br.com.aaribeiro.icarros.dto.questions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DealRequestDTO {
    @NotNull(message = "Filed customerName can not be empty or null")
    private String customerName;

    @NotNull(message = "Filed customerName can not be empty or null")
    private List<ItemsRequestDTO> items;
}
