// BoardCanvas:  an object that displays a the Catan Board
//
// CS 201 Exam 2

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
import java.awt.event.*;

@SuppressWarnings("serial") // to avoid Eclipse warning
public class BoardCanvas extends Canvas implements MouseListener {

    // instance variables

    // TODO: Add data structures here

    public static Hex hexes[][] = new Hex[5][5];


    //passed down from CantanApplet

    //stores the road information
    public static HashMap<String, Road> roadStore;

    //stores the house information
    public static HashMap<String, House> houseStore;

    //stores all players in an array.
    public static Player[] players;

    protected CatanApplet parent;  // access to main applet class

    // fonts used:
    static final Font textFont = new Font("Arial", Font.PLAIN, 14);
    static final Font nodeFont = new Font("Arial", Font.PLAIN, 10);
    static final Font nodeFontBold = new Font("Arial", Font.BOLD, 11);

    // constructor
    public BoardCanvas(CatanApplet app, HashMap roadStore, HashMap houseStore,
                        Player[] players) {
        parent = app;
        this.roadStore = roadStore;
        this.houseStore = houseStore;
        this.players = players;
        // ADD new arrays here
        populateHexes();
    }

    // instance methods
    // ADD INSTANCE METHODS HERE TO CHANGE THE ARRAYS

    // repaint this canvas
    public void paint (Graphics g) {

        // turn on anti-aliasing for smoother lines
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        Dimension d = getSize();        // size of canvas

        int size = (int)d.getWidth()/14;

        int centerX = 5 * size; // initial values in top left
        int centerY = 2 * size;
        int w = (int)(Math.sqrt(3) * size); // set width of hex
        int h = 2 * size; //set height of hex

        for (int i = 0; i<3; i++) {// first row of hexs
            //set x to centerX, set y to centerY, and set that it is not a ghost
            drawHex(g, centerX, centerY, size);
            centerX += w;
        }
        centerX-=w/2;
        centerY += .75 * h;
        for (int i = 0; i<4 ; i++ ) { // second row of hexs
            drawHex(g, centerX, centerY, size);
            centerX -= w;
        }
        for (int i =0; i<3; i++){ // test loop
            System.out.println("X is: " + hexes[0][i].getCol());
        }
        centerX+=w/2;
        centerY += .75 * h;
        for (int i = 0; i<5 ; i++ ) { // third row of hexes
            drawHex(g, centerX, centerY, size);
            centerX += w;
        }
        centerX-=3* w/2;
        centerY += .75 * h;
        for (int i = 0; i<4 ; i++ ) { // fourth row of hexes
            drawHex(g, centerX, centerY, size);
            centerX -= w;
        }
        centerX+=3* w/2;
        centerY += .75 * h;
        for (int i = 0; i<3 ; i++ ) { // fifth row of hexes
            drawHex(g, centerX, centerY, size);
            centerX += w;
        }

        int diceRoll1 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        int diceRoll2 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        String compRoll = Integer.toString(diceRoll1 + diceRoll2);

        String diceRoll = "Dice roll: " + compRoll; // comp roll will have to specified outside
        centerString(g, diceRoll, (int)d.getWidth()/2, 12*size);

        House testHouse = new House(2, 30, 30, false, 0);
        houseStore.put("233433", testHouse);
        drawHomes(g, houseStore, 0);

    }
    public static void drawHex(Graphics g, int centerX, int centerY,
                                int size){
        for (int i = 1; i<=6 ; i++) {
            //System.out.println("I is: " + i);
            g.drawLine(drawHexBetter(g, centerX, centerY, size, i, true), //x1
                        drawHexBetter(g, centerX, centerY, size, i, false),//y1
                        drawHexBetter(g, centerX, centerY, size, i+1, true), //x2
                        drawHexBetter(g, centerX, centerY, size, i+1, false));//y2
        }
    }

    public static int drawHexBetter(Graphics g, int centerX, int centerY,
                                        int size, int i, boolean isX) {


        int angle_deg = 60 * i - 30;
        double angle_rad = Math.toRadians(angle_deg);
        // System.out.printf("for %d, angle deg is %d and rads = %f", i, angle_deg, angle_rad);
        int x1 = centerX + (int)(size * Math.cos(angle_rad));
        int y1 = centerY + (int)(size * Math.sin(angle_rad));
        // System.out.printf("for %d, x1 is %d and y1 is %d", i, x1, y1);
        if (isX) {
            // System.out.println("Returning X, i = " + i);
            return x1;
        } else {
            // System.out.println("Returning Y i = " + i);
            return y1;
        }
    }

    //populates the board with hexes
    public static void populateHexes() {
        for (int i = 0; i <= 4; i++) {
            for (int x = 0; x <= 4; x++) {
                Hex hex = new Hex(0, 0, 0, x, i, 0,true);// change to be random
                hexes[x][i] = hex;
            }
        }
    }

    //draw houses and cities
    public static void drawHomes(Graphics g, HashMap<String, House> store, int playerColor){
        //use iterator to iterate through hashmap of houses
        Iterator it = store.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String, House> entry = (Map.Entry)it.next();
            House home = entry.getValue();
            int state = home.getState();

            // change the Color
            setColor(g, playerColor);

            //check what is contained at the vertex
            if (state == 0 || state == 1) {
                continue;
            }
            if (state == 2){
                int rad = 2;
                g.fillOval(home.getX() - rad, home.getY() - rad, 2*rad, 2*rad);
            }
            if (state == 3) {
                int rad = 4;
                g.fillOval(home.getX() - rad, home.getY() - rad, 2*rad, 2*rad);
            }
        }

    }
    // set the color of the home/roads based off integer
    public static void setColor(Graphics g, int c){
        if (c == 0) { // red player
            g.setColor(Color.red);
        } else if (c == 1) {// green player
            g.setColor(Color.green);
        } else if (c == 2) { // yellow
            g.setColor(Color.yellow);
        } else if (c == 3) { // blue player
            g.setColor(Color.blue);
        }
    }

    public static void drawRoads(Graphics g, HashMap<String, Road> store, int playerColor){
        //use iterator to iterate through hashmap of houses
        Iterator it = store.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String, Road> entry = (Map.Entry)it.next();
            Road road = entry.getValue();
            int state = road.getState();

            // change the Color
            setColor(g, playerColor);

            //check what is contained at the vertex
            if (state == 0 || state == 1) {
                continue;
            }
            if (state == 2){
                g.drawLine(road.getX1(), road.getY1(), road.getX2(),
                            road.getY2());
            }
        }
    }

        public void mouseClicked(MouseEvent event) {
            Point p = event.getPoint();
            // get coordinates of event
            int x = p.x;
            int y = p.y;

            if (parent.getButton() == 0) {
                // do nothing

            } else if (parent.getButton() == 1) { // build CITY
                int playerColor = 0; // set player color

                // find vertex of build location
                // call to nearest three hex function
                //find nearest three and average X and Y coordinates
                // build house at those coords

                // House city = new House(0, x,y, true, playerColor);

                //add to houseStore
            } else if (parent.getButton() == 2) { // build SETTLEMENT
                // TODO: Add calls to CatanOpps
                // looks for adjacent roads -- sees if player has roads there
                // checks if the spot is "reserved"

                // if so
                    // returns error message
                //else

                    // puts a house into the hashmap at the coordinates
                    // reserve houses adjacent
                //get player color
                // repaint
                int playerColor = 0; // set player color
                // House city = new House(x,y, false, playerColor);
            } else if (parent.getButton() == 3) { // build ROAD
                // TODO: Add calls to CatanOpps
                // checks for adjacent roads of that player
                    // same int value
                // if they are the same
                    // add int value of player to the RodeStore using a new
                    //road object
                // else print error message!

            }

        }

        // because we have implemented the MouseListener
        public void mouseEntered(MouseEvent event) { }
        public void mouseExited(MouseEvent event) { }
        public void mouseReleased(MouseEvent event) { }
        public void mousePressed(MouseEvent event) { }
        public void mouseDragged(MouseEvent event) { }
        public void mouseMoved(MouseEvent event) { }

    // draw a String centered at x, y
    public static void centerString(Graphics g, String s, int x, int y) {
        FontMetrics fm = g.getFontMetrics(g.getFont());
        int xs = x - fm.stringWidth(s)/2;
        int ys = y + fm.getAscent()/3;
        g.drawString(s, xs, ys);
    }
}
