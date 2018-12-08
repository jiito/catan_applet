// Player class to store player data
//
//CS201 final project - Catan

public class Player {

    //instance variables:

    //playerColor variable, 0 = red, 1 = green, 2 = yellow, 3 = blue.
    public int playerColor;

    //resource variables, how many resources each player has
    public int brick;
    public int rock;
    public int sheep;
    public int wheat;
    public int wood;

    public int vp; // victory points

    public Player(int playerColor, int brick, int rock, int sheep, int wheat, int wood) {
        this.playerColor = playerColor;
        this.brick = brick;
        this.rock = rock;
        this.sheep = sheep;
        this.wheat = wheat;
        this.wood = wood;
    }

    //getters 
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
    public int getVP(){
        return vp;
    }

    //setters
    public void setBrick(int r) {
        this.brick+= r;
    }

    public void setRock(int r) {
        this.rock+= r;
    }

    public void setSheep(int r) {
        this.sheep+= r;
    }

    public void setWheat(int r) {
        this.wheat+= r;
    }

    public void setWood(int r) {
        this.wood+= r;
    }
    public void setVP(int i){
        this.vp+=i;
    }

    public void takeBrick(int r) {
        this.brick-= r;
    }

    public void takeRock(int r) {
        this.rock-= r;
    }

    public void takeSheep(int r) {
        this.sheep-= r;
    }

    public void takeWheat(int r) {
        this.wheat-= r;
    }

    public void takeWood(int r) {
        this.wood-= r;
    }
}
