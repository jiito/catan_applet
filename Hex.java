//Hex class
//
//CS201 final project - Catan

public class Hex {

    //instance variables
    //resources numbers correspond as follows:
    //0 = brick, 1 = sheep, 2 = wheat, 3 = wood 4 = rock.
    public int type;
    //amount owed to each player
    public int owedR;
    public int owedB;
    public int owedG;
    public int owedO;

    // cartesian coordinates
    public int x;
    public int y;
    public int size;

    // coordinates
    public int row;
    public int col;

    // activation number
    public int diceRoll;

    //whether Hex is painted or not
    public boolean ghost;

    public Hex(int type, int x, int y, int size, int row, int col, int diceRoll, boolean ghost) {
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

        this.ghost = ghost;
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

    public int getSize() {
        return size;
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
    public boolean getGhost() {
        return ghost;
    }
}
