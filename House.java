// File to define the House/Settlement classes

public class House {

    public int state;
    public int x;
    public int y;
    public boolean isCity;
    public int playerColor;


    //constructor
    public House(int state, int x, int y, boolean isCity, int playerColor){
        this.state = state;
        this.x = x;
        this.y = y;
        this.isCity = isCity;
        this.playerColor = playerColor;
    }

    // getters
    public int getState(){
        return this.state;
    }
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
