package com.company;

import java.util.Random;

public class ComputerPlayer extends Player {
    Random r;


    public ComputerPlayer() {
        super();
        super.name = "Computer";
        r = new Random();
    }


    @Override
    public char createPlayer(char playerChar) {

        char compChar;
        if (playerChar == 'X' || playerChar == 'x') {
            compChar = 'O';
        } else {
            compChar = 'X';
        }
        this.character = compChar;

        System.out.printf("Opponent by the name %s and marker %c added \n", this.name, compChar);

        return compChar;
    }

    @Override
    public boolean makeMove(GameBoard g) {

        int choice;
        System.out.println(this.name + " is choosing where to put the marker: ");

        //Låtsas att datorn tänker
        try{
            Thread.sleep(1500);

        } catch (InterruptedException x){
            Thread.currentThread().interrupt();

        }

        while (true) {
            choice = r.nextInt(g.getMoves().size()) + 1;

            //System.out.println("Comp choice " + choice );

                if (g.getMoves().get(choice - 1).getPlayer() == null) { // se till att platsen är tom
                    g.getMoves().get(choice - 1).setPlayer(this);

                    System.out.printf("%s chooses to put his mark %c on number %d \n", this.name, this.character, choice );
                    // se om det är ett vinnande move
                    if (g.isWinningMove(choice - 1)) {
                        addWin();  // plussa på datorns poäng
                        return true;
                    }
                    return false;
                }
        }
    }
}
