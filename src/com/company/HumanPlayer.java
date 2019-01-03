package com.company;

import java.util.Scanner;

public class HumanPlayer extends Player {

    @Override
    public char createPlayer(char playerChar) {

        System.out.println("Player, what's your name?");
        this.name = sc.nextLine();
        System.out.println(this.name + " what marker would you like to use?");
        char test;
        //check så inte nummer
        while (true) {
            if (!sc.hasNextInt()) {
                test = sc.next().charAt(0);
                if (Character.toUpperCase(test) != Character.toUpperCase(playerChar)) { //[0]
                    this.character = test;//sc.next().charAt(0);
                    //Returnerar en char så att ingen annan kan välja samma
                    return this.character;
                }
                System.out.println("Character choosen by another player ");

            }
            System.out.println("Please choose another character");
            sc.nextLine();
        }
    }

    @Override
    public boolean makeMove(GameBoard g) {
        while (true) {
            System.out.println(this.name + " please choose a number to add your marker: ");
            try {  //se till att det är en siffra
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice > 0 && choice <= g.getMoves().size()) {  // se till att siffran existerar på boardet
                    // se till att platsen är tom, -1 för att Arraylistan börjar på 0 inte 1
                    if (g.getMoves().get(choice - 1).getPlayer() == null) {
                        g.getMoves().get(choice - 1).setPlayer(this);
                        // se om det är ett vinnande move
                        if (g.isWinningMove(choice - 1)) {
                            addWin();  // plussa på spelarens poäng
                            return true;
                        }
                        return false;
                    } else {
                        System.out.println("A player has already put their marker on " + choice);
                    }
                } else {
                    System.out.println("Not a valid number, choose a number above 0 and below " + (g.getMoves().size() + 1));
                }
            } catch (Exception e) {
                System.out.println("Not a number, try again ");
                sc.nextLine();
            }

        }
    }
}