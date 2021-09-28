package br.com.aaribeiro.icarros.service;

import br.com.aaribeiro.icarros.component.Question6Component;
import br.com.aaribeiro.icarros.dto.question6.InfosConstructorDTO;
import br.com.aaribeiro.icarros.dto.question6.MyLatestResearchResponseDTO;
import br.com.aaribeiro.icarros.dto.question6.InfosDriverResponseDTO;
import br.com.aaribeiro.icarros.model.Historic;
import br.com.aaribeiro.icarros.repository.HistoricRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@AllArgsConstructor
@Slf4j
@Service
public class Question6Service {
    private final Question6Component question6Component;
    private final HistoricRepository historicRepository;
    private ObjectMapper mapper = new ObjectMapper();

    public InfosDriverResponseDTO getInfosDriver(String name){
        JsonNode jsonNode = question6Component.getInfosDriver(name);

        JsonNode driver = jsonNode.get("MRData").get("DriverTable").get("Drivers").get(0);

        log.info(driver.toPrettyString());

        historicRepository.save(Historic.builder()
                .date(LocalDateTime.now())
                .content(driver.toPrettyString())
                .build());

        return mapper.convertValue(driver, InfosDriverResponseDTO.class);
    }

    public InfosConstructorDTO getInfosConstructor(String name){
        JsonNode jsonNode = question6Component.getInfosConstructor(name);

        JsonNode constructor = jsonNode.get("MRData").get("ConstructorTable").get("Constructors").get(0);

        log.info(constructor.toPrettyString());

        historicRepository.save(Historic.builder()
                .date(LocalDateTime.now())
                .content(constructor.toPrettyString())
                .build());

        return mapper.convertValue(constructor, InfosConstructorDTO.class);
    }

    public JsonNode getGeneralClassificationOfConstructors(int year){
        JsonNode jsonNode = question6Component.getGeneralClassificationOfConstructors(year);

        JsonNode classification = jsonNode.get("MRData").get("StandingsTable").get("StandingsLists").get(0);

        log.info(classification.toPrettyString());

        historicRepository.save(Historic.builder()
                .date(LocalDateTime.now())
                .content(classification.toPrettyString())
                .build());

        return classification;
    }

    public Object getMyLatestResearch() {
        return historicRepository.findAllOrderByIdDesc().stream()
                .map(historic -> {
                    try {
                        return MyLatestResearchResponseDTO.builder()
                                .uuid(historic.getUuid())
                                .date(historic.getDate().toString())
                                .content(mapper.readTree(historic.getContent()).toPrettyString())
                                .build();
                    } catch (Exception e){

                    }
                    return null;
                });
    }
}