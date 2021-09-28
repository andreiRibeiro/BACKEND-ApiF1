package br.com.aaribeiro.icarros.service;

import br.com.aaribeiro.icarros.dto.questions.DealRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class QuestionsServiceTest {

    @Autowired
    QuestionsService questionsService;

    @BeforeEach
    public void setUp(){
    }

    @Test
    @DisplayName("Deve encontrar no resultado as expressões em suas respectivas quantidades")
    public void question1_ReturnListNumbersWithFizzBuss_WhenSucessful(){
        List<String> results = Arrays.asList(questionsService.question1().split("\n"));
        Assertions.assertEquals(27, (int) results.stream().filter(s -> s.equals("Fizz")).count());
        Assertions.assertEquals(14, (int) results.stream().filter(s -> s.equals("Buzz")).count());
        Assertions.assertEquals(6, (int) results.stream().filter(s -> s.equals("FizzBuzz")).count());
    }

    @Test
    @DisplayName("Deve retornar o total de estrelas das strings")
    public void question2_ReturnTotalValueOfStars_WhenSucessful(){
        Long result = questionsService.question2(new String[]{"---", "*--", "**-", "***", "*-*"});
        Assertions.assertEquals(8, result);
    }

    @Test
    @DisplayName("Deve encontrar nas entradas o padrão de formatação e retornar total encontrado")
    public void question3_ReturnCountCorrectValues_WhenSucessful(){
        Long result = questionsService.question3(new String[]{"16:16", "15:11", "11:11", "05:55", "23:23", "11:19", "04:40", "00:30"});
        Assertions.assertEquals(4, result);
    }

    @Test
    @DisplayName("Deve aplicar a regra de multiplacação e retornar o somatório entre as listas de entrada")
    public void question4_ReturnCountCorrectValues_WhenSucessful(){
        Long result = questionsService.question4(new int[]{9,3,7,5}, new int[]{2,0,8,2});
        Assertions.assertEquals(29, result);
    }

    @Test
    @DisplayName("Deve retornar o valor total do pedido conforme template")
    public void question5_ReturnTotalValueOfStars_WhenSucessful() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String json1 = "{\"customerName\":\"DeathStar\",\"items\":[{\"name\":\"Exhaust vents\",\"value\":1500},{\"name\":\"Exhaust vents\",\"value\":1000},{\"name\":\"Exhaust vents\",\"value\":599.99}]}";
        String json2 = "{\"customerName\":\"Andrei Antonio Ribeiro\",\"items\":[{\"name\":\"Exhaust vents\",\"value\":4500},{\"name\":\"Exhaust vents\",\"value\":6099.99}]}";

        String result = questionsService.question5(objectMapper.readValue(json1, DealRequestDTO.class));
        questionsService.question5(objectMapper.readValue(json2, DealRequestDTO.class));

        Assertions.assertEquals("TotalPrice: 3099.99", result.split("\\|")[1].trim());
    }

    @Test
    @DisplayName("Deve calcular com base nas linhas de entrada as coordenadas finais")
    public void question7_ReturnCalculationCoordinates_WhenSucessful(){
        String entryLines = "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM";

        String result = questionsService.question7(entryLines);

        Assertions.assertEquals("1 3 N", result.split("\n")[0]);
        Assertions.assertEquals("5 1 E", result.split("\n")[1]);
    }
}
