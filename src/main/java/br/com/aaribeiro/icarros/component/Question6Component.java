package br.com.aaribeiro.icarros.component;

import br.com.aaribeiro.icarros.config.EargastPropertiesConfig;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Component
public class Question6Component {
    private final EargastPropertiesConfig eargastPropertiesConfig;

    public JsonNode getInfosDriver(String name){
        String url = String.format("%s/f1/drivers/%s.json", eargastPropertiesConfig.getApiErgast(), name);
        ResponseEntity<JsonNode> response = new RestTemplate().exchange(url, HttpMethod.GET, null, JsonNode.class);
        return response.getBody();
    }

    public JsonNode getInfosConstructor(String name){
        String url = String.format("%s/f1/constructors/%s.json", eargastPropertiesConfig.getApiErgast(), name);
        ResponseEntity<JsonNode> response = new RestTemplate().exchange(url, HttpMethod.GET, null, JsonNode.class);
        return response.getBody();
    }

    public JsonNode getGeneralClassificationOfConstructors(int year){
        String url = String.format("%s/f1/%s/constructorStandings.json", eargastPropertiesConfig.getApiErgast(), year);
        ResponseEntity<JsonNode> response = new RestTemplate().exchange(url, HttpMethod.GET, null, JsonNode.class);
        return response.getBody();
    }
}
