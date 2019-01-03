package com.company;

public class Move {
    private int x;
    private int y;
    private Player player;

    public Move() {
    }

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Player getPlayer() {
        return player;
    }


    public void setPlayer(Player player) {
        this.player = player;
    }


    //isWinningMove





}
