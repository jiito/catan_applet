// BoardCanvas:  an object that displays a the Catan Board
//
// CS 201 Exam 2

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
import java.awt.event.*;

@SuppressWarnings("serial") // to avoid Eclipse warning
public class BoardCanvas extends Canvas implements MouseListener, MouseMotionListener {

    // instance variables

    // TODO: Add data structures here

    //stores an even distribution of hex resource types
    public static int[] hexResources = new int[19];

    //stores an even distribution of all diceRolls
    public static int[] diceRolls = new int[19];

    //stores all the hexes in a 2D array
    public static Hex hexes[][] = new Hex[5][5];


    //stores all players in an array
    public static Player[] players = new Player[4];

        //declares player objects
    public static Player red = new Player(0);
    public static Player blue = new Player(1);
    public static Player green = new Player(2);
    public static Player orange = new Player(3);

    public static Color brick =  new Color(244, 69, 66);
    public static Color sheep =  new Color(167, 168, 166);
    public static Color wheat =  new Color(173, 147, 0);
    public static Color wood =  new Color(163, 85, 1);
    public static Color rock =  new Color(61, 60, 60);


    //passed down from CantanApplet

    //stores the road information
    public static HashMap<Integer, Road> roadStore;

    //stores the house information
    public static HashMap<Integer, House> houseStore;
    protected CatanApplet parent;  // access to main applet class

    // fonts used:
    static final Font textFont = new Font("Arial", Font.PLAIN, 14);
    static final Font nodeFont = new Font("Arial", Font.PLAIN, 10);
    static final Font nodeFontBold = new Font("Arial", Font.BOLD, 11);

    private int whichButton;

    // constructor
    public BoardCanvas(CatanApplet app, HashMap roadStore, HashMap houseStore) {
        parent = app;

        //initialize data structures:

            //populates hexResources
            populateHexResources();

            //populates diceRolls
            populateDiceRolls();

            //populates players
            populatePlayers();

            //populates hexes
            populateHexes();

        this.roadStore = roadStore;
        this.houseStore = houseStore;

        // start with the first player
        parent.currentPlayer = players[0];
        parent.players = this.players;
        parent.hexes = this.hexes;
        //this.players = players;
        // ADD new arrays here
        // populateHexes();

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

        for (int i = 1; i<4; i++) {// first row of hexs
            //set x to centerX, set y to centerY, and set that it is not a ghost
            drawHex(g, centerX, centerY, size);
            parent.hexes[0][i].setX(centerX);
            parent.hexes[0][i].setY(centerY);
            centerX += w;
        }
        centerX-=w/2;
        centerY += .75 * h;
        for (int i = 0; i<4 ; i++ ) { // second row of hexs
            drawHex(g, centerX, centerY, size);
            parent.hexes[1][i].setX(centerX);
            parent.hexes[1][i].setY(centerY);
            centerX -= w;
        }
        centerX+=w/2;
        centerY += .75 * h;
        for (int i = 0; i<5 ; i++ ) { // third row of hexes
            drawHex(g, centerX, centerY, size);
            parent.hexes[2][i].setX(centerX);
            parent.hexes[2][i].setY(centerY);
            centerX += w;
        }
        centerX-=3* w/2;
        centerY += .75 * h;
        for (int i = 0; i<4 ; i++ ) { // fourth row of hexes
            drawHex(g, centerX, centerY, size);
            parent.hexes[3][i].setX(centerX);
            parent.hexes[3][i].setY(centerY);
            centerX -= w;
        }
        centerX+=3* w/2;
        centerY += .75 * h;
        for (int i = 1; i<4 ; i++ ) { // fifth row of hexes
            drawHex(g, centerX, centerY, size);
            parent.hexes[4][i].setX(centerX);
            parent.hexes[4][i].setY(centerY);
            centerX += w;
        }

        int diceRoll1 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        int diceRoll2 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        String compRoll = Integer.toString(diceRoll1 + diceRoll2);

        // update parent object
        parent.diceRoll = Integer.parseInt(compRoll);

        String diceRoll = "Dice roll: " + compRoll; // comp roll will have to specified outside
        centerString(g, diceRoll, (int)d.getWidth()/2, 12*size);

        // House testHouse = new House(2, 30, 30, false, 0);
        // houseStore.put("233433", testHouse);
        drawResourcesDice(g);
        drawHomes(g, houseStore, parent.currentPlayer.getPlayerColor());
        drawRoads(g, roadStore, parent.currentPlayer.getPlayerColor());

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

    public void drawResourcesDice(Graphics g){
        for (int i= 0; i < parent.hexes.length ; i++) {
            for (int j =0; j <parent.hexes[i].length; j++) {
                Hex hex = parent.hexes[i][j];
                if (!hex.getGhost()) {
                    if(hex.getType()== 0) //brick
                        g.setColor(brick);
                    if(hex.getType()== 1) //sheep
                        g.setColor(sheep);
                    if(hex.getType()== 2) //wheat
                        g.setColor(wheat);
                    if(hex.getType()== 3) //wood
                        g.setColor(wood);
                    if(hex.getType()== 4) //rock
                        g.setColor(rock);

                    String diceRoll = Integer.toString(hex.getDiceRoll());
                    centerString(g, diceRoll, hex.getX(), hex.getY());
                }
            }
        }
    }

    //populates the board with hexes
    public static void populateHexes() {
        for (int i = 0; i <= 4; i++) {
            for (int x = 0; x <= 4; x++) {
                Hex hex = new Hex(0, 0, 0,0, x, i, 0,false);// change to be random
                hexes[x][i] = hex;
            }
        }

        hexes[0][0].setGhost(true);
        hexes[0][4].setGhost(true);
        hexes[1][4].setGhost(true);
        hexes[3][4].setGhost(true);
        hexes[4][0].setGhost(true);
        hexes[4][4].setGhost(true);

        shuffleArray(diceRolls);
        shuffleArray(hexResources);

        int count = 0;
        for (int i = 0; i <= 4; i++) {
            for (int x = 0; x <= 4; x++) {
                if (! hexes[x][i].getGhost()) {
                    hexes[x][i].setDiceRoll(diceRolls[count]);
                    hexes[x][i].setType(hexResources[count]);
                    count++;
                }
            }
        }
    }

    public void populateHexResources() {

        for (int i = 0; i <= 3; i++) { //brick
            hexResources[i] = 0;
        }
        for (int i = 4; i <= 7; i++) { //sheep
            hexResources[i] = 1;
        }
        for (int i = 8; i <= 11; i++) { //wheat
            hexResources[i] = 2;
        }
        for (int i = 12; i <= 15; i++) { //wood
            hexResources[i] = 3;
        }
        for (int i = 16; i <= 18; i++) { //rock
            hexResources[i] = 4;
        }
    }

    //this shuffle is still a little glitchy because it repeats numbers,
    //but works for testing purposes
    static void shuffleArray(int[] ar) {

        int noOfElements = ar.length;

        for (int i = 0; i < noOfElements; i++) {

            int s = i + (int)(Math.random() * (noOfElements - i));

            int temp = ar[s];
            ar[s] = ar[i];
            ar[i] = temp;

        }
    }

    //populates dice rolls
    private void populateDiceRolls() {
        diceRolls[0] = 2;
        diceRolls[1] = 3;
        diceRolls[2] = 3;
        diceRolls[3] = 4;
        diceRolls[4] = 4;
        diceRolls[5] = 5;
        diceRolls[6] = 5;
        diceRolls[7] = 6;
        diceRolls[8] = 6;
        diceRolls[9] = 7;
        diceRolls[10] = 8;
        diceRolls[11] = 8;
        diceRolls[12] = 9;
        diceRolls[13] = 9;
        diceRolls[14] = 10;
        diceRolls[15] = 10;
        diceRolls[16] = 11;
        diceRolls[17] = 11;
        diceRolls[18] = 12;
    }

    //populates players
    private void populatePlayers() {
        players[0] = red;
        players[1] = blue;
        players[2] = green;
        players[3] = orange;

        //to test resources
        players[0].setRock(5);
        players[1].setBrick(100);

    }

    //draw houses and cities
    public static void drawHomes(Graphics g, HashMap<Integer, House> store, int playerColor){
        //use iterator to iterate through hashmap of houses
        Iterator it = store.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Integer, House> entry = (Map.Entry)it.next();
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

    public static void drawRoads(Graphics g, HashMap<Integer, Road> store, int playerColor){
        //use iterator to iterate through hashmap of houses
        Iterator it = store.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Integer, Road> entry = (Map.Entry)it.next();
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




    // action handler for buttons
    // public void actionPerformed(ActionEvent evt) {
    //     if (evt.getSource() == parent.endButton) {
    //         // TODO: Add calls to CatanOpps
    //         // switched player object?
    //         this.whichButton = 0;
    //         System.out.
    //     } else if (evt.getSource() == parent.cityButton) {
    //         this.whichButton = 1;
    //
    //     } else if (evt.getSource() == parent.settlementButton) {
    //         this.whichButton = 2;
    //
    //
    //     } else if (evt.getSource() == parent.roadButton) {
    //         this.whichButton =3;
    //     }
    //
    // }

    public void mouseClicked(MouseEvent event) {
        System.out.println("CLICKED");
        Point p = event.getPoint();
        // get coordinates of event
        int x = p.x;
        int y = p.y;



        if (parent.whichButton == 0) {
            // do nothing

        } else if (parent.whichButton == 1) { // build CITY
            int color = 0; // set player color
            System.out.println("CITY");

            Hex[] nearest = CatanOps.nearestThreeHexes(x, y, hexes);
            int vertex = CatanOps.makeVertexID(nearest[0], nearest[1], nearest[2]);

            if (houseStore.get(vertex).getState() == 0) {
                int avgX = (nearest[0].getX() + nearest[1].getX()
                                +nearest[2].getX())/3;
                int avgY = (nearest[0].getY() + nearest[1].getY()
                                +nearest[2].getY())/3;
                House city = new House(2, avgX, avgY, true, parent.currentPlayer.getPlayerColor());
                houseStore.put(vertex, city);
            } else System.out.print("you cant build a city here");

            this.repaint();

            // find vertex of build location
            // call to nearest three hex function
            //find nearest three and average X and Y coordinates
            // build house at those coords

            // House city = new House(0, x,y, true, color);

            //add to houseStore
        } else if (parent.whichButton == 2) { // build SETTLEMENT
            int color = 2; // set player color
            System.out.println("SETTLEMENT");


            Hex[] nearest = CatanOps.nearestThreeHexes(x, y, hexes);
            int vertex = CatanOps.makeVertexID(nearest[0], nearest[1], nearest[2]);

            if (houseStore.get(vertex).getState() == 0) {
                int avgX = (nearest[0].getX() + nearest[1].getX()
                                +nearest[2].getX())/3;
                int avgY = (nearest[0].getY() + nearest[1].getY()
                                +nearest[2].getY())/3;
                House settle = new House(1, avgX, avgY, false, parent.currentPlayer.getPlayerColor());
                houseStore.put(vertex, settle);

                //reserves roads

            } else System.out.print("you cant build a settlement here");

            this.repaint();
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
             // set player color
            // House city = new House(x,y, false, color);
        } else if (parent.whichButton == 3) { // build ROAD
            //int color = 2; // set player color
            System.out.println("ROAD");


            Hex[] nearest = CatanOps.nearestTwoHexes(x, y, hexes);
            int path = CatanOps.makePathID(nearest[0], nearest[1]);
            int[] vertices = CatanOps.adjacentVerticesToPath(path, hexes);


            if (roadStore.get(path).getState() == 2) {
                int x1 = nearest[0].getX() + nearest[0].getSize();
                int y1 = nearest[1].getY();
                int x2 = nearest[1].getX() - nearest[1].getSize();
                int y2 = nearest[1].getY();
                Road road = new Road(2, x1, y1, x2, y2);
                roadStore.put(path, road);

                //reserve other roads?
            } else System.out.print("you cant build a road here");

            this.repaint();
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
    public void mouseEntered(MouseEvent event) {
        System.out.print("you cant build a road here");
    }
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
