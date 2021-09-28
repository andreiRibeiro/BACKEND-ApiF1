package br.com.aaribeiro.icarros.controller.publico;

import br.com.aaribeiro.icarros.dto.questions.DealRequestDTO;
import br.com.aaribeiro.icarros.dto.questions.Question4DTO;
import br.com.aaribeiro.icarros.service.QuestionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/questions")
@Api(tags = "Processa informações para resolução do desafio backend (Público)")
public class QuestionsController {
    private final QuestionsService questionsService;

    @GetMapping(value = "/1", produces = MediaType.TEXT_PLAIN_VALUE)
    @ApiOperation(value = "Programa deve imprimir números 1 a 100. Para multiplos de 3 imprime Fizz, de 5 imprime Buzz e de ambos FizzBuzz")
    public ResponseEntity<String> question1(){
        return ResponseEntity.status(HttpStatus.OK).body(questionsService.question1());
    }

    @PostMapping(value = "/2", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    @ApiOperation(value = "Programa deve receber uma lista de strings ex.: ['*--', '***'] e somar de acordo com regra quantas estrelas existem nas listas")
    public ResponseEntity<String> question2(@RequestBody String[] list){
        return ResponseEntity.status(HttpStatus.OK).body(questionsService.question2(list).toString());
    }

    @PostMapping(value = "/3", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    @ApiOperation(value = "Programa deve receber uma lista de strings ex.: ['16:16', '23:23'] e somar quais horarios se encaixam no padrão especificado")
    public ResponseEntity<String> question3(@RequestBody String[] list){
        return ResponseEntity.status(HttpStatus.OK).body(questionsService.question3(list).toString());
    }

    @PostMapping(value = "/4", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    @ApiOperation(value = "Programa deve receber duas listas de inteiros representando vitorias e empates ex.: [2, 0, 8, 2] e totalizar de acordo com regra a equipe campeã")
    public ResponseEntity<String> question4(@RequestBody @Valid Question4DTO question4DTO){
        return ResponseEntity.status(HttpStatus.OK).body(questionsService.question4(question4DTO.getWins(), question4DTO.getTies()).toString());
    }

    @PostMapping(value = "/5", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    @ApiOperation(value = "Programa deve receber um pedido e seus itens conforme DTO de entrada. O objetivo é retornar o id e valor total do pedido de acordo com o solicitante")
    public ResponseEntity<String> question5(@RequestBody @Valid DealRequestDTO dealRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(questionsService.question5(dealRequestDTO));
    }

    @PostMapping(value = "/7", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    @ApiOperation(value = "Programa deve receber instruções com no minimo 3 linhas, onde cada linha tem padrão de orientações para executar os movimentos do(s) robo(s) e retornar o calculo de sua ultima posição")
    public ResponseEntity<String> question7(@RequestBody String entryLines){
        return ResponseEntity.status(HttpStatus.OK).body(questionsService.question7(entryLines));
    }
}