//Hex class
//
//CS201 final project - Catan

public class Hex {

    //instance variables
    //resources numbers correspond as follows:
    //0 = brick, 1 = rock, 2 = sheep, 3 = wheat, 4 = wood.
    public int type;
    //amount owed to each player
    public int owedR;
    public int owedB;
    public int owedG;
    public int owedO;

    // cartesian coordinates
    public int x;
    public int y;

    // coordinates
    public int row;
    public int col;

    // activation number
    public int diceRoll;


    public Hex(int type, int x, int y, int row, int col, int diceRoll) {
        this.type = type;
        this.owedR = 0;
        this.owedB = 0;
        this.owedG = 0;
        this.owedO = 0;

        this.x = x;
        this.y = y;
        this.row = row;
        this.col = col;
        this.diceRoll = diceRoll;
    }

    // getters
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public int getDiceRoll() {
        return diceRoll;
    }
}
