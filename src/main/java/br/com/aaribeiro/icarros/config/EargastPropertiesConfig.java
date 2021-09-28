package br.com.aaribeiro.icarros.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class EargastPropertiesConfig {
    @Value("${api.ergast}")
    private String apiErgast;
}
