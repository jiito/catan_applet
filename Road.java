// Class to contain verticies of road

public class Road {

    //instance variables
    protected int state;
    protected int x1;
    protected int x2;

    protected int y1;
    protected int y2;

    //constructor
    public Road(int state, int x1, int y1, int x2, int y2){
        this.state = state;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    // getters
    public int getState(){
        return state;
    }

    public int getX1(){
        return x1;
    }

    public int getY1(){
        return y1;
    }

    public int getX2(){
        return x2;
    }

    public int getY2(){
        return y2;
    }

}
