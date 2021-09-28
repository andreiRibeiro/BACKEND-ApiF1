package br.com.aaribeiro.icarros.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Question7Component {
    private char direction;
    private int northSouth;
    private int easthWest;
    private int maxAxisX;
    private int maxAxisY;

    public String execute(String commands, int northSouth, int easthWest, char direction, int maxAxisX, int maxAxisY) {
        this.direction = direction;
        this.northSouth = northSouth;
        this.easthWest = easthWest;
        this.maxAxisX = maxAxisX;
        this.maxAxisY = maxAxisY;

        for (int i = 0; i < commands.length(); i++) {
            char command = commands.charAt(i);

            if ('L' == command || 'R' == command) this.updateDirection(this.direction, command);
            else this.updateCoordinates();

//            log.info("Command: " + command);
//            log.info("Direction: " + this.direction);
//            log.info("NorthSouth: " + this.northSouth);
//            log.info("EasthWest: " + this.easthWest);
//            log.info("------------------------");
        }
        return this.easthWest + " " + this.northSouth + " " + this.direction;
    }

    /**
     * Atualiza coordenadas do robo no eixo x,y
     *     x|
     *      |
     *      |_________y
     */
    private void updateCoordinates(){
        if (this.direction == 'W'){
            if (easthWest > 0) easthWest--;
            else easthWest = 0;
        }
        else if (this.direction == 'S'){
            if (northSouth > 0) northSouth--;
            else northSouth = 0;
        }
        else if (this.direction == 'E'){
            if (this.easthWest <= this.maxAxisY) easthWest++;
        }
        else if (this.direction == 'N'){
            if (this.northSouth <= this.maxAxisX) northSouth++;
        }
    }

    /**
     * Atualiza direção do robo conforme sua rotação
     *              N
     *          W       L
     *              S
     **/
    private void updateDirection(char currentDirection, char newDirection) {

        //NORTE
        if (currentDirection == 'N' && newDirection == 'L') {
            this.direction = 'W';
        }
        else if (currentDirection == 'N' && newDirection == 'R') {
            this.direction = 'E';
        }

        //SUL
        else if (currentDirection == 'S' && newDirection == 'L') {
            this.direction = 'E';
        }
        else if (currentDirection == 'S' && newDirection == 'R') {
            this.direction = 'W';
        }

        //LESTE
        else if (currentDirection == 'E' && newDirection == 'L') {
            this.direction = 'N';
        }
        else if (currentDirection == 'E' && newDirection == 'R') {
            this.direction = 'S';
        }

        //OESTE
        else if (currentDirection == 'W' && newDirection == 'L') {
            this.direction = 'S';
        }
        else if (currentDirection == 'W' && newDirection == 'R') {
            this.direction = 'N';
        }
    }
}