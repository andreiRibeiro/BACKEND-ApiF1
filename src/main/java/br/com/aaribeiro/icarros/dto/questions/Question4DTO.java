package br.com.aaribeiro.icarros.dto.questions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Question4DTO {
    @NotNull(message = "Filed wins can not be empty or null")
    private int[] wins;

    @NotNull(message = "Filed ties can not be empty or null")
    private int[] ties;
}
