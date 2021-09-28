package br.com.aaribeiro.icarros.service;

import br.com.aaribeiro.icarros.component.Question7Component;
import br.com.aaribeiro.icarros.dao.DealDAO;
import br.com.aaribeiro.icarros.dto.questions.DealRequestDTO;
import br.com.aaribeiro.icarros.dto.questions.DealResponseDTO;
import br.com.aaribeiro.icarros.model.Deal;
import br.com.aaribeiro.icarros.model.Item;
import br.com.aaribeiro.icarros.repository.DealRepository;
import br.com.aaribeiro.icarros.repository.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Slf4j
@Service
public class QuestionsService {
    private final Question7Component question7Component;
    private final DealRepository dealRepository;
    private final ItemRepository itemRepository;
    private final DealDAO dealDAO;

    public String question1(){
        StringBuilder result = new StringBuilder();

        List<Integer> range = IntStream.range(1, 101)
                .boxed()
                .collect(Collectors.toList());

        for (int value : range){
            if ((value % 5) == 0 && (value % 3) == 0){
                System.out.println("FizzBuzz");
                result.append("FizzBuzz\n");
                continue;
            }
            if ((value % 3) == 0){
                System.out.println("Fizz");
                result.append("Fizz\n");
                continue;
            }
            if ((value % 5) == 0){
                System.out.println("Buzz");
                result.append("Buzz\n");
            }
            else {
                System.out.println(value);
                result.append(value + "\n");
            }
        }
        return result.toString();
    }

    public Long question2(String[] results){
        long stars = Arrays
                .stream(results)
                .mapToLong(value -> value.trim().replace("-", "").length())
                .sum();

        log.info("Total stars: " + stars);
        return stars;
    }

    public Long question3(String[] results){
        long moments = Arrays.stream(results).mapToLong(time -> {
            if (this.validateTemplate(time)) return 1;
            return 0;
        }).sum();

        log.info("Total moments: " + moments);
        return moments;
    }

    private boolean validateTemplate(String time){
        String hour = time.split(":")[0];
        String minute = time.split(":")[1];

        String hourId1 = String.valueOf(hour.charAt(0));
        String hourId2 = String.valueOf(hour.charAt(1));
        String minuteId1 = String.valueOf(minute.charAt(0));
        String minuteId2 = String.valueOf(minute.charAt(1));

        String abab = hourId1 + hourId2 + ":" + hourId1 + hourId2;
        String aabb1 = hourId1 + hourId1 + ":" + minuteId1 + minuteId1;
        String aabb2 = hourId1 + hourId1 + ":" + minuteId2 + minuteId2;
        String abba = hourId1 + hourId2 + ":" + hourId2 + hourId1;
        String aaaa = hourId1 + hourId1 + ":" + hourId1 + hourId1;

        if (time.equals(aaaa)){
            log.info(time + " | AA:AA");
            return true;
        }
        else if (time.equals(abab)){
            log.info(time + " | AB:AB");
            return true;
        }
        else if (time.equals(aabb1)){
            log.info(time + " | AA:BB");
            return true;
        }
        else if (time.equals(aabb2)){
            log.info(time + " | AA:BB");
            return true;
        }
        else if (time.equals(abba)){
            log.info(time + " | AB:BA");
            return true;
        }
        return false;
    }

    public Long question4(int[] wins, int[] ties){
        Long points = Collections.max(IntStream.range(0, wins.length)
                .mapToLong(index -> (wins[index] * 3L) + ties[index])
                .boxed()
                .collect(Collectors.toList())
        );
        log.info("Total points: {}", points);
        return points;
    }

    public String question5(DealRequestDTO dealRequestDTO){
        this.createDealsAndItems(dealRequestDTO);

        List<DealResponseDTO> deals = dealDAO.searchDealsByCustomerName(dealRequestDTO.getCustomerName());

        StringBuilder linesResult = new StringBuilder();

        deals.forEach(deal -> {
            String result = String.format("OrderID: %s | TotalPrice: %s\n", deal.getOrderId(), deal.getTotalPrice());
            log.info(result);
            linesResult.append(result);
        });
        return linesResult.toString();
    }

    private void createDealsAndItems(DealRequestDTO dealRequestDTO){
        dealRepository.save(Deal.builder()
                .customerName(dealRequestDTO.getCustomerName())
                .date(LocalDateTime.now())
                .items(dealRequestDTO.getItems()
                        .stream()
                        .map(itemsRequestDTO -> itemRepository.save(Item.builder()
                                .name(itemsRequestDTO.getName() + " model " + UUID.randomUUID().toString().split("-")[0])
                                .value(itemsRequestDTO.getValue())
                                .build()
                        )).collect(Collectors.toList())
                )
                .build()
        );
    }

    public String question7(String entryLines) {
        StringBuilder results = new StringBuilder();
        int maxAxisX = Integer.parseInt(entryLines.split("\n")[0].split(" ")[0]);
        int maxAxisY = Integer.parseInt(entryLines.split("\n")[0].split(" ")[1]);

        for (int i = 1; i < entryLines.split("\n").length; i++){
            String line1 = entryLines.split("\n")[i];

            String commands = entryLines.split("\n")[i + 1];
            int easthWest =  Integer.parseInt(line1.split(" ")[0]);
            int northSouth = Integer.parseInt(line1.split(" ")[1]);
            char direction = line1.split(" ")[2].charAt(0);

            results.append(question7Component.execute(commands, northSouth, easthWest, direction, maxAxisX, maxAxisY) + "\n");
            i++;
        }
        System.out.println(results);
        return results.toString();
    }
}