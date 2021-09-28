package br.com.aaribeiro.icarros.dto.question6;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MyLatestResearchResponseDTO {
    private String uuid;
    private String date;
    private String content;
}
