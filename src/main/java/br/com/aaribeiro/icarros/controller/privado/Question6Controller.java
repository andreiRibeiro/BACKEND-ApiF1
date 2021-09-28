package br.com.aaribeiro.icarros.controller.privado;

import br.com.aaribeiro.icarros.dto.question6.InfosConstructorDTO;
import br.com.aaribeiro.icarros.dto.question6.InfosDriverResponseDTO;
import br.com.aaribeiro.icarros.service.Question6Service;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/f1")
@Api(tags = "Consulta dados da F1 a partir da integração com API Ergast (Privado)")
public class Question6Controller {
    private final Question6Service question6Service;

    @GetMapping(value = "/driver/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Obtem informações de um motorista conforme seu nome")
    public ResponseEntity<InfosDriverResponseDTO> getInfosDriver(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(question6Service.getInfosDriver(name));
    }

    @GetMapping(value = "/constructor/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Obtem informações de um fabricante conforme seu nome")
    public ResponseEntity<InfosConstructorDTO> getInfosConstructor(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(question6Service.getInfosConstructor(name));
    }

    @GetMapping(value = "/classification-constructors/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Obtem classificação geral dos fabricantes conforme o ano informado")
    public ResponseEntity<JsonNode> getGeneralClassificationOfConstructors(@PathVariable int year){
        return ResponseEntity.status(HttpStatus.OK).body(question6Service.getGeneralClassificationOfConstructors(year));
    }

    @GetMapping(value = "/my-latest-research", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Obtem listagem de suas consultas feitas, ordenada a partir da mais atual")
    public ResponseEntity<?> getMyLatestResearch(){
        return ResponseEntity.status(HttpStatus.OK).body(question6Service.getMyLatestResearch());
    }
}
