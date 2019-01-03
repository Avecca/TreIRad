package com.company;

import java.util.Random;

public class SuperComputerPlayer extends Player {

    Random r;

    public SuperComputerPlayer() {
        super();
        super.name = "Superman";
        r = new Random();
    }


    @Override
    public char createPlayer(char playerChar) {

        char compChar;
        if (playerChar == 'O' || playerChar == 'o') {
            compChar = 'X';
        } else {
            compChar = 'O';
        }

        this.character = compChar;

        System.out.printf("Opponent by the name of %s and marker %c added \n", this.name, compChar);

        return compChar;
    }

    @Override
    public boolean makeMove(GameBoard g) {
        //TODO g.getMoves()get(i).getX som variabel namn så mer läsbart kanske

        int choice;
        System.out.println(this.name + " is choosing where to put the marker: ");

        //Låtsas att datorn tänker
        try {
            Thread.sleep(1500);

        } catch (InterruptedException x) {
            Thread.currentThread().interrupt();

        }


        for (int i = 0; i < g.getMoves().size(); i++) {

            if (g.getMoves().get(i).getPlayer() instanceof SuperComputerPlayer) {
/*              System.out.println("I är " + i);
                System.out.println("i +1 = " + (i + 1));*/

                //XLedsTest
                if ((i + 1) < g.getMoves().size()) { //nästa X är inom boardet

                    //Är nästa move inom samma x skala och inte upptaget
                    if (g.getMoves().get(i).getX() == g.getMoves().get(i + 1).getX() && g.getMoves().get(i + 1).getPlayer() == null) {
                        choice = i + 1;

                        //Sätt marken
                        g.getMoves().get(choice).setPlayer(this);
                        announceMove(choice + 1);
                        if (g.isWinningMove(choice)) {
                            addWin();  // plussa på spelarens poäng
                            return true;
                        }

                        return false;
                        //Är nästa X move upptagen av spelaren redan(eftersom 3 i rad vinner titta nästa)
                    } else if (g.getMoves().get(i).getX() == g.getMoves().get(i + 1).getX() && g.getMoves().get(i + 1).getPlayer() ==
                            this) { // this är g.getMoves().get(i).getPlayer()
                        if (i + 2 < g.getMoves().size()) {
                            if (g.getMoves().get(i).getX() == g.getMoves().get(i + 2).getX() && g.getMoves().get(i + 2).getPlayer() == null) {
                                choice = i + 2;

                                //Sätt marken
                                g.getMoves().get(choice).setPlayer(this);
                                announceMove(choice + 1);
                                if (g.isWinningMove(choice)) {
                                    addWin();  // plussa på spelarens poäng
                                    return true;
                                }
                                return false;
                            }
                        }
                    }
                }

                if (i > 0) {  //Större än 0 medför att minus 1 kan bli 0
                    // System.out.println("testar x minus");
                    if (g.getMoves().get(i).getX() == g.getMoves().get(i - 1).getX() && g.getMoves().get(i - 1).getPlayer() == null) {
                        choice = i - 1;
                        //TODO om 3x3board om x + 1 är motspelaren, gör inte detta utan gör y- Svårare variant kanske
                        g.getMoves().get(choice).setPlayer(this);
                        announceMove(choice + 1);
                        if (g.isWinningMove(choice)) {
                            addWin();  // plussa på spelarens poäng
                            return true;
                        }
                        return false;
                    }
                }

                //YLedsTest //TODO testa på y-led behövs verkligen Y == Y checken?
                if ((i + g.getBoardRows() < g.getMoves().size())) {   //+g.getBoardRows();
               /*     System.out.println("Testar y nleds i: " + i + "size: " + g.getMoves().size());
                    System.out.println(g.getMoves().get(i).getY() + " + 3 ger y " + g.getMoves().get(i + g.getBoardRows()).getY());*/
                    if (g.getMoves().get(i).getY() == g.getMoves().get(i + g.getBoardRows()).getY()
                            && g.getMoves().get(i + g.getBoardRows()).getPlayer() == null) {
                        choice = i + g.getBoardRows();

                        g.getMoves().get(choice).setPlayer(this);
                        announceMove(choice + 1);
                        if (g.isWinningMove(choice)) {
                            addWin();
                            return true;
                        }

                        return false;  //sitter redan Spelarens Marker där
                    } else if (g.getMoves().get(i).getY() == g.getMoves().get(i + g.getBoardRows()).getY() &&
                            g.getMoves().get(i + g.getBoardRows()).getPlayer() == this) { //Kolla 2 rader ner
                        //System.out.println("2ggr Rows = " + (g.getBoardRows() * 2));
                        if (i + (g.getBoardRows() * 2) < g.getMoves().size()) { //Är 2 rader ner inom boardet?

                            if (g.getMoves().get(i).getY() == g.getMoves().get(i + (g.getBoardRows() * 2)).getY() &&
                                    g.getMoves().get(i + (g.getBoardRows() * 2)).getPlayer() == null) {
                                choice = i + (g.getBoardRows() * 2);

                                g.getMoves().get(choice).setPlayer(this);
                                announceMove(choice + 1);
                                if (g.isWinningMove(choice)) {
                                    addWin();
                                    return true;
                                }
                                return false;
                            }
                        }
                    }
                }

                //  if( Y led - rows){
                if ((i - g.getBoardRows()) >= 0) {
                    //System.out.println("Minus y led");
                    if (g.getMoves().get(i).getY() == g.getMoves().get(i - g.getBoardRows()).getY() && g.getMoves().get(i - g.getBoardRows()).getPlayer() == null) {

                        choice = i - g.getBoardRows();

                        g.getMoves().get(choice).setPlayer(this);
                        announceMove(choice + 1);
                        if (g.isWinningMove(choice)) {
                            addWin();
                            return true;
                        }
                        return false;
                    }
                }
                //TODO else if diagonalen- Kanske ännu en svårighetsgrad- Defensiv dator?
            }
        }

        // if choice är null gör en random
        //Hittar inget move av SuperComputer som den kan lägga en marker brevid
        while (true) {
            choice = r.nextInt(g.getMoves().size()); //+1
            if (g.getMoves().get(choice).getPlayer() == null) { // se till att platsen är tom, GÖRS OVAN //-1
                g.getMoves().get(choice).setPlayer(this);  //-1

                announceMove(choice + 1); // + 1 eftersom 0 inte syns
                // se om det är ett vinnande move
                if (g.isWinningMove(choice)) { // -1
                    addWin();  // plussa på spelarens poäng
                    return true;
                }
                return false;
            }
        }
    }

    public void announceMove(int choice) {
        System.out.printf("%s chooses to put his mark %c on number %d \n", this.name, this.character, choice);
    }

}
