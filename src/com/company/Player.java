package com.company;

import java.util.Scanner;

public abstract class Player {
    protected String name;
    protected char character;
    protected int wins;
    protected Scanner sc;

    public Player() {
        sc = new Scanner(System.in);
    }

    public Player(String name, char character) {
        this.name = name;
        this.character = character;
        this.wins = 0;
    }

    public char getCharacter() {
        return character;
    }

    public int getWins() {
        return wins;
    }

    public void addWin() {
        this.wins += 1;
    }

    public String getName() {
        return name;
    }

    public abstract char createPlayer(char playerChar);

    public abstract boolean makeMove(GameBoard g);



    @Override
    public String toString() {
        return "Player{" +
                "Name='" + name + '\'' +
                ", Marker=" + character +
                ", Number of wins=" + wins +
                '}';
    }

    //region Discarded code
    /*{
        System.out.println("Player, what's your name?");
        this.name = sc.nextLine();
        System.out.println(this.name + " what character would you like to use?");
        char test;
        //check så inte nummer
        while (true) {
            if (!sc.hasNextInt()) {
                test = sc.next().charAt(0);
                if (test != playerChar) { //[0]
                    this.character =  test;//sc.next().charAt(0);
                    return this.character;
                }
                System.out.println("Character choosen by another player ");

            }
            System.out.println("Please choose another character");
            sc.nextLine();
        }

        //TODO så inte använda samma char
        //return this.character;
    }*/
    //endregion
}
