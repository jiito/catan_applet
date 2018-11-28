//Tile class
//
//CS201 final project - Catan

public class Tile {

    //instance variables
    //resources numbers correspond as follows:
    //0 = brick, 1 = rock, 2 = sheep, 3 = wheat, 4 = wood.
    public int type;
    //amount owed to each player
    public int owedR;
    public int owedB;
    public int owedG;
    public int owedO;

    public Tile(int type) {
        this.type = type;
        this.owedR = 0;
        this.owedB = 0;
        this.owedG = 0;
        this.owedO = 0;
    }

    public int getOwedR() {
        return owedR;
    }

    public int getOwedB() {
        return owedB;
    }

    public int getOwedG() {
        return owedG;
    }

    public int getOwedO() {
        return owedO;
    }

    public int getType() {
        return type;
    }
}
