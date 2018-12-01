// File to define the House/Settlement classes

public class House {

    public int x;
    public int y;
    public int playerColor;

    //constructor
    public static House(int x, int y, int playerColor){
        this.x = x;
        this.y = y;
        this.playerColor = playerColor;
    }

    // getters
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getPlayerColor() {
        return this.playerColor;
    }

}
