package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GameBoard {

    private int boardRows;
    private int noInRowToWin;
    private ArrayList<Move> moves;

    private Scanner sc;

    //List<Point>


    public GameBoard(int boardRows) {
        sc = new Scanner(System.in);
        moves = new ArrayList<Move>();
        for (int i = 0; i < boardRows; i++) {
            for (int j = 0; j < boardRows; j++) {
                moves.add(new Move(i, j));
            }
        }
        this.boardRows = boardRows;
        this.noInRowToWin = 3;
    }

/*    public GameBoard(int coords) {

    }*/

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public int getBoardRows() {
        return boardRows;
    }


    public void showBoard() {
        int lastX = 0;

        String upper = "+----+";
        String middle = " | ";
        // System.out.println(moves.size() + " " + (boardRows - 1));

        for (int i = 0; i < boardRows - 1; i++) {
            upper += "----+";

        }

        int number = 1;  //Placera ut nummer på boardet där ingen satt sin marker

        System.out.println(upper);
        for (int j = 0; j < boardRows; j++) { //boardSize
            System.out.print("| ");

            for (int i = 0; i < boardRows; i++) {  //boardSize
                if (moves.get(number - 1).getPlayer() != null) {
                    System.out.print(" " + moves.get(number - 1).getPlayer().getCharacter() + middle);

                } else {
                    if (number < 10) {
                        System.out.print(" " + (number) + middle);
                    } else {
                        System.out.print(number + middle);
                    }
                }
                number += 1;

            }
            System.out.println();
            System.out.println(upper);
        }

    }

    public boolean isWinningMove(int choice) {

        Player player = moves.get(choice).getPlayer();
        int xNum = moves.get(choice).getX();
        int yNum = moves.get(choice).getY();
        int noInRoW = 0;

        //TODO Titta på movet och inte hela boardet- titta på movets Spelar ägare

        //X-led   -->  --  Om summan av alla ihoplagda x -edsnummer blir == boardSize(3)
        //System.out.println("Testar x");
        //System.out.println("col " + colNum);
        for (Move move : moves) {
     /*     System.out.println(move.getX() + " " + move.getY());
            System.out.println("in row " + noInRoW);*/
            if (move.getX() == xNum && (move.getPlayer() == null || move.getPlayer() != player)) {
                // System.out.println("removing poäng");
                noInRoW = 0;

                //Större board, om inget på stället, nolla noInRow, om x i rad så return true
            } else if (move.getX() == xNum && move.getPlayer() == player) {
                //System.out.println("Adding point");
                noInRoW++;  //Tillsvidare-
                if (noInRoW == noInRowToWin) {
                    return true;
                }
            }
        }
        //Nolla noInRow för att testa nästa vinstsätt
        noInRoW = 0;

        //Y-led  ^
        // System.out.println("testar y");
        for (Move move : moves) {
            if (move.getY() == yNum && (move.getPlayer() == null || move.getPlayer() != player)) {
                noInRoW = 0;
            } else if (move.getY() == yNum && move.getPlayer() == player) {
                noInRoW++;
                if (noInRoW == noInRowToWin) {
                    return true;
                }
            }
        }
        noInRoW = 0;

        //Diagonalen 00 11 22
        int digSum = xNum - yNum;

        for (Move move : moves) {
            if (move.getX() - move.getY() == digSum && (move.getPlayer() == null || move.getPlayer() != player)) {
                noInRoW = 0;
            } else if (move.getX() - move.getY() == digSum && (move.getPlayer() == player)) {
                noInRoW++;
                if (noInRoW == noInRowToWin) {
                    return true;
                }
            }
        }
        noInRoW = 0;
/*
        //Bakvänd diagonal*/
        int coordSum = xNum + yNum;

/*      System.out.println(coordSum);
        System.out.println("Testar bakvänd");*/

        for (Move move : moves) {
            //System.out.println(move.getX() + move.getY());  //CoordSUm eller boardRows -1??
            if (move.getX() + move.getY() == coordSum && (move.getPlayer() == null || move.getPlayer() != player)) {
                // System.out.println("Inte en spelare eller char");
                //break;
                noInRoW = 0;
            } else if (move.getX() + move.getY() == coordSum && (move.getPlayer() == player)) {
                // System.out.println("xy i rad");
                noInRoW++;
                if (noInRoW == noInRowToWin) {
                    return true;
                }
            }
        }
        //Ingen vinst åt något håll
        return false;

    }


    //region Discarded code
    //Används inte
    public boolean makeMove(Player player) {

        while (true) {
            System.out.println(player.getName() + " please choose a number to add your marker: ");
            int choice = sc.nextInt();
            sc.nextLine();

            //TODO se till sa siffran existerar på boardet
            if (this.moves.get(choice - 1).getPlayer() == null && choice > 0 && choice <= moves.size()) {
                this.moves.get(choice - 1).setPlayer(player);

                if (isWinningMove(choice - 1)) {
                    player.addWin();
                    return true;
                }
                return false;
            } else if (choice < 0 && choice > moves.size()) {
                System.out.println("Not a valid number, choose a number above 0 and below " + (moves.size() + 1));
            } else {
                System.out.println("A player has already put their marker on " + choice);
            }
        }
        //Kommer aldrig hända
        // return false;


    }

/*            if (rowNum + colNum == boardSize-1){ // 0+ 2 == 3-1    2+ 0 == 3-1  04 == 5-1  13 == 5-1 41 == 5-1
        for (Move move : moves) {
            if (move.getY() + move.getX() == boardSize -1 && (move.getPlayer() == null || move.getPlayer().getCharachter() != x)){
                break;
            }else if(move.getY() + move.getX() == boardSize -1 && move.getPlayer().getCharachter() == x){
                noInRoW ++;
            }
        }
    }



        if (noInRoW == boardSize) {
        return true;
    }
    noInRoW = 0;*/

/*
        if (noInRoW == boardRows) {
            return true;
        }*/




/*        for (int i = 0; i < boardSize ; i++) {
            if (moves.get(choice).getPlayer().getCharachter() != x){
                break; //not a win
            }

        }*/

    //TODO Fixa så detta fungerar på större plan
    //diagonalen- ena hållet
/*        if (yNum == xNum) { //i krysset 00 11 22 osv
            for (Move move : moves) {
                if (move.getY() == move.getX() && (move.getPlayer() == null || move.getPlayer().getCharacter() != playerCharacter)) {
                    noInRoW = 0;
                    //break;
                } else if (move.getX() == move.getY() && move.getPlayer().getCharacter() == playerCharacter) {
                    noInRoW++;
                    if (noInRoW == noInRowToWin){
                        return true;
                    }
                }
            }
        }*/

   /*            if((boardRows - Gått igenom) < noInRowToWin) { om raden - dom som vi gått igen utan att gitta ngåto <
                break;  //annorlunda om fler än 5 i rad osv
                     }*/
    //endregion

}
