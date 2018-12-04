//Hex class
//
//CS201 final project - Catan

public class Player {

    //instance variables:

    //playerColor variable, 0 = red, 1 = blue, 2 = green, 3 = orange.
    public int playerColor;

    //resource variables, how many resources each player has
    public int brick;
    public int rock;
    public int sheep;
    public int wheat;
    public int wood;

    public Player(int playerColor) {
        this.playerColor = playerColor;
        this.brick = 0;
        this.rock = 0;
        this.sheep = 0;
        this.wheat = 0;
        this.wood = 0;
    }

    public int getPlayerColor() {
        return playerColor;
    }

    public int getBrick() {
        return brick;
    }

    public int getRock() {
        return rock;
    }

    public int getSheep() {
        return sheep;
    }

    public int getWheat() {
        return wheat;
    }

    public int getWood() {
        return wood;
    }
}