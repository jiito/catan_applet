// File to define the House/Settlement classes

public class House {

    public int x;
    public int y;
    public boolean isCity;
    public int playerColor;


    //constructor
    public static House(int x, int y, boolean isCity, int playerColor){
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

    public boolean getIsCity(){
        return this.isCity;
    }

}
