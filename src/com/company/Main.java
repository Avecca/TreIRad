package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<Player>();
        Scanner sc = new Scanner(System.in);
        boolean playing = true;
        boolean winner = false;
        GameBoard g;
        //ArrayList<Move> moves;
        int boardRows = 0;
        int boardSize = 0;
        boolean addComputer = false;
        char playerChar = ' ';//new char[2];§§
        char difficulty = ' ';
        Random r = new Random();


        System.out.println("Hi! Welcome to Tic Tac Toe!");
        System.out.println();
        String againstPlayer;

        while (true) {
            System.out.println("Want to play against another player? Y/N");
            againstPlayer = sc.next().substring(0, 1).toUpperCase(); //sc.nextLine();
            if (againstPlayer.equals("N")) {
                addComputer = true;
                break;
            } else if (againstPlayer.equals("Y")) {
                break;
            }
            System.out.println("Not a valid choice");
        }


        //System.out.println(addComputer);

        //Skapa Spelare

        for (int i = 0; i < 2; i++) {
            if (addComputer) {
                System.out.println("Want to face an easy opponent? Y/N ");
                difficulty = sc.next().charAt(0);
                //Switch för kanske adda olika svåroghetsgrader
                switch (difficulty) {
                    case 'y':
                    case 'Y':
                        players.add(new ComputerPlayer());
                        break;
                    case 'n':
                    case 'N':
                        players.add(new SuperComputerPlayer());
                        break;
                    default:
                        //Failar dom på Y/N så random dator! Skylla sig själv!
                        int rDifficulty = r.nextInt(2);

                        if (rDifficulty > 0) {
                            players.add(new SuperComputerPlayer());
                        } else {
                            players.add(new ComputerPlayer());
                        }
                        System.out.println("Facing a random computer player!");
                        break;
                }
                addComputer = false;
            } else {
                players.add(new HumanPlayer());
            }
        }
        //Låt spelaren välja namn och karaktär
        for (Player player : players) {
            playerChar = player.createPlayer(playerChar);
        }
        System.out.println();


        //medans vi spelar
        while (playing) {
            //Nytt spel, skapar tomt board med medskickad boardsize på bredden ,
            // håller koll på moves

            while (boardSize == 0) {
                System.out.println("Size of the board you want to play on? MIN 3, MAX 10");
                if (sc.hasNextInt()) {// Se till att svaret är en int
                    boardRows = sc.nextInt();
                    if (boardRows >= 3 && boardRows <= 10) {   //Under 100 och över 3
                        boardSize = boardRows * boardRows;
                        sc.nextLine();
                    } else {
                        System.out.println("Choose a boardsize between 3 and 10");
                    }
                } else {
                    System.out.println("Please choose a number ");
                    sc.next();
                }
            }

            //Instansiera Gameboard och skapa en array av Moves i den
            g = new GameBoard(boardRows);

            //Visa hur boardet ser ut
            System.out.println("This is your board ");
            g.showBoard();


            //Spela rundor, så länge det inte finns en vinnare eller alla platser är tagna
            while (!winner && boardSize > 0) {  //TODO Byt namn till movesLeft
                System.out.println();
                //System.out.println("ny runda på while loopen " + winner + boardSize);
                //Varje spelare tar en tur
                for (Player player : players) {
                    //System.out.println("ny runda på playerlistan " + boardSize);


                    //Spelaren tar en tur och ser om det är ett vinnande drag
                    //Vinnande drag ger poäng till spelaren
                    winner = player.makeMove(g);

                    //ta bort ett tillgängligt drag
                    boardSize--;

                    //visa nya spelplanen
                    g.showBoard();

                    //Om draget var vinnardraget, annonsera det och avsluta rundan
                    if (winner) {
                        System.out.printf("Congrats %s is the winner of this round! \n", player.getName());
                        //System.out.println("winner är nu " + winner);
                        break;
                    }
                    //Om draget var sista draget möjligt, avsluta rundan
                    if (boardSize <= 0) {
                        break;
                    }
                    //winner = g.checkWinner();
                }
            }

            //Spelet fick slut på drag utan att det fanns en vinnare
            if (!winner) {

                System.out.println("Sorry game was a draw!");
            }

            //Visa poäng statestik
            for (Player player : players) {
                System.out.printf("%s number of wins: %d \n", player.getName(), player.getWins());
            }

            //Fråga om dom vill spela en runda till
            System.out.println();
            String oneMore;
            while (true) {
                System.out.println("Do you want to play another round? Y/N");
                oneMore = sc.next().substring(0, 1).toUpperCase();

                //Vill inte spela igen
                if (oneMore.equals("N")) {
                    playing = false;
                    System.out.println("Thanks for playing Tic Tac Toe!");
                    for (Player player : players) {
                        System.out.println(player);
                    }
                    break;

                } else if (oneMore.equals("Y")) {  // Vill spela igen, resetta boardsize
                    winner = false;
                    boardSize = 0;
                    //Minst Wins ska börja nästa runda
                    Collections.sort(players, new SortPlayersByWins());
                    System.out.printf("Player %s begins next round \n", players.get(0).name);
                    break;
                }
                System.out.println("Not a valid input");
            }

        }

    }

    //region Discarded code
    /*            //Spelaren är en dator
            if (player instanceof ComputerPlayer) { //player.getClass() ==  ComputerPlayer.class)
                playerChar = player.createPlayer(playerChar);
            } else {
                playerChar = player.createPlayer(playerChar);

            }*/
    //endregion

}

//Sortera players listan efter antalet winster, minst först
class SortPlayersByWins implements Comparator<Player> {
    public int compare(Player p1, Player p2) {
        return p1.wins - p2.wins;
    }
}



