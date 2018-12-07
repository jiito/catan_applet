// BoardCanvas:  an object that displays a the Catan Board
//
// CS 201 Exam 2

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

@SuppressWarnings("serial") // to avoid Eclipse warning
public class BoardCanvas extends Canvas implements MouseListener, MouseMotionListener {

    // instance variables

    // TODO: Add data structures here

    //stores an even distribution of hex resource types
    public static int[] hexResources = new int[19];

    //stores an even distribution of all diceRolls
    public static int[] diceRolls = new int[19];

    //stores all the hexes in a 2D array
    public static Hex hexes[][] = new Hex[8][8];

    public static int[] startingPositions = new int[8];

    //stores all players in an array
    public static Player[] players = new Player[4];

    //stores what kind of turn it is; true = start, false = reg.
    public static boolean turnType = true;

    //the console
    public static String talkBack = "Place a settlement and a road to start.";

        //declares player objects
    public static Player red = new Player(0,2,0,1,1,2);
    public static Player green = new Player(1,0,0,0,0,0);
    public static Player blue = new Player(2,0,0,0,0,0);
    public static Player yellow = new Player(3,0,0,0,0,0);

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
    public int hexSize;

    public Graphics g;

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

            //populatres starting startingPositions
            populateStartingPositions();

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

        checkWin(g);
        // turn on anti-aliasing for smoother lines
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        Dimension d = getSize();        // size of canvas

        int size = (int)d.getWidth()/14;
        this.hexSize = size;

        int centerX = 2 * size; // initial values in top left
        int centerY = 2 * size;
        int w = (int)(Math.sqrt(3) * size); // set width of hex
        int h = 2 * size; //set height of hex

        for (int i = 0; i<7; i++) {// zeroth row of hexs
            //set x to centerX, set y to centerY, and set that it is not a ghost
            parent.hexes[0][i].setX(centerX);
            parent.hexes[0][i].setY(centerY);
            centerX += w;
        }
        centerX-=w/2;
        centerY += .75 * h;
        for (int i = 7; i>0; i--) {// first row of hexs
            //set x to centerX, set y to centerY, and set that it is not a ghost
            parent.hexes[1][i].setX(centerX);
            parent.hexes[1][i].setY(centerY);
            if (!parent.hexes[1][i].getGhost()) {
                drawHex(g, centerX, centerY, size);
            }
            centerX -= w;
        }
        centerX+=w/2;
        centerY += .75 * h;
        for (int i = 0; i<7 ; i++ ) { // second row of hexs
            parent.hexes[2][i].setX(centerX);
            parent.hexes[2][i].setY(centerY);
            if (!parent.hexes[2][i].getGhost()) {
                drawHex(g, centerX, centerY, size);
            }
            centerX += w;
        }
        centerX-=w/2;
        centerY += .75 * h;
        for (int i = 7; i>0 ; i-- ) { // third row of hexes
            parent.hexes[3][i].setX(centerX);
            parent.hexes[3][i].setY(centerY);
            if (!parent.hexes[3][i].getGhost()) {
                drawHex(g, centerX, centerY, size);
            }
            centerX -= w;
        }
        centerX+= w/2;
        centerY += .75 * h;
        for (int i = 0; i<7 ; i++ ) { // fourth row of hexes
            parent.hexes[4][i].setX(centerX);
            parent.hexes[4][i].setY(centerY);
            if (!parent.hexes[4][i].getGhost()) {
                drawHex(g, centerX, centerY, size);
            }
            centerX += w;
        }
        centerX-= w/2;
        centerY += .75 * h;
        for (int i = 7; i>0 ; i-- ) { // fifth row of hexes
            parent.hexes[5][i].setX(centerX);
            parent.hexes[5][i].setY(centerY);
            if (!parent.hexes[5][i].getGhost()) {
                drawHex(g, centerX, centerY, size);
            }
            centerX -= w;
        }
        centerX+=w/2;
        centerY+=.75 *h;
        for (int i = 0; i<7 ; i++ ) { // 6th row of hexes

            parent.hexes[6][i].setX(centerX);
            parent.hexes[6][i].setY(centerY);
            centerX -= w;
        }
        centerX+=w/2;
        centerY+=.75 *h;
        for (int i = 7; i<0 ; i-- ) { // 7th row of hexes

            parent.hexes[7][i].setX(centerX);
            parent.hexes[7][i].setY(centerY);
            centerX -= w;
        }
        //initializeHouses();
        centerString(g, talkBack, d.width/2, d.height-12*(d.height/13));




        // update parent object

        String compRoll= "";
        if (parent.whichButton == 4 || parent.whichButton == 0) {

            int diceRoll1 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
            int diceRoll2 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
            compRoll = Integer.toString(diceRoll1 + diceRoll2);
            parent.diceRoll = Integer.parseInt(compRoll);
        }
        String diceRoll = "Dice roll: " + parent.diceRoll; // comp roll will have to specified outside
        int r = 30;
        g.drawRect((int)((d.getWidth()/2)-(r*2)),(12*size)-(r/2),r*4,r);
        centerString(g, diceRoll, (int)d.getWidth()/2, 12*size);

        // House testHouse = new House(2, 30, 30, false, 0);
        // houseStore.put("233433", testHouse);
        drawRoads(g, roadStore);
        drawResourcesDice(g);
        drawHomes(g, houseStore);
        //assignResources();
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

    // add resources to player based off dice roll
    public void assignResources(){
        for (int i= 0; i < parent.hexes.length ; i++) {
            for (int j =0; j <parent.hexes[i].length; j++) {
                Hex hex = parent.hexes[i][j];
                if (hex.getDiceRoll() == parent.diceRoll) {
                    if(hex.getType()== 0){ //brick
                        parent.players[0].setBrick(hex.getOwed(0));
                        parent.players[1].setBrick(hex.getOwed(1));
                        parent.players[2].setBrick(hex.getOwed(2));
                        parent.players[3].setBrick(hex.getOwed(3));
                    }
                    if(hex.getType()== 1){ //sheep
                        parent.players[0].setSheep(hex.getOwed(0));
                        parent.players[1].setSheep(hex.getOwed(1));
                        parent.players[2].setSheep(hex.getOwed(2));
                        parent.players[3].setSheep(hex.getOwed(3));
                    }
                    if(hex.getType()== 2){ //wheat
                        parent.players[0].setWheat(hex.getOwed(0));
                        parent.players[1].setWheat(hex.getOwed(1));
                        parent.players[2].setWheat(hex.getOwed(2));
                        parent.players[3].setWheat(hex.getOwed(3));
                    }
                    if(hex.getType()== 3){ //wood
                        parent.players[0].setWood(hex.getOwed(0));
                        parent.players[1].setWood(hex.getOwed(1));
                        parent.players[2].setWood(hex.getOwed(2));
                        parent.players[3].setWood(hex.getOwed(3));
                    }
                    if(hex.getType()== 4){ //rock
                        parent.players[0].setRock(hex.getOwed(0));
                        parent.players[1].setRock(hex.getOwed(1));
                        parent.players[2].setRock(hex.getOwed(2));
                        parent.players[3].setRock(hex.getOwed(3));
                    }
                }

            }
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
                    //String ex = Integer.toString(hex.getRow());
                    //String why = Integer.toString(hex.getCol());
                    //String owed = Integer.toString(hex.getOwed(0));
                    g.fillOval(hex.getX()-20,hex.getY()-20,40,40);
                    g.setColor(Color.white);
                    g.fillOval(hex.getX()-12,hex.getY()-12,24,24);
                    g.setColor(Color.black);
                    //centerString(g, ex + " " + why, hex.getX(), hex.getY());
                    centerString(g, diceRoll, hex.getX(), hex.getY());
                }
            }
        }
    }

    //populates the board with hexes
    public static void populateHexes() {
        for (int i = 0; i <= 7; i++) {
            for (int x = 0; x <= 7; x++) {
                Hex hex = new Hex(0, 0, 0, 0, x, i, 0, false);// change to be random
                hexes[x][i] = hex;
            }
        }

        //set ghost hexes
        for (int i = 0; i <= 7; i++) {
            hexes[0][i].setGhost(true);
        }
        for (int i = 0; i <= 7; i++) {
            hexes[6][i].setGhost(true);
        }
        for (int i = 0; i <= 7; i++) {
            hexes[i][0].setGhost(true);
        }
        for (int i = 0; i <= 7; i++) {
            hexes[i][6].setGhost(true);
        }
        for (int i = 0; i <= 7; i++) {
            hexes[i][7].setGhost(true);
        }
        for (int i = 0; i <= 7; i++) {
            hexes[7][i].setGhost(true);
        }


        hexes[1][1].setGhost(true);
        hexes[1][5].setGhost(true);
        hexes[2][5].setGhost(true);
        hexes[4][5].setGhost(true);
        hexes[5][1].setGhost(true);
        hexes[5][5].setGhost(true);


        shuffleArray(diceRolls);
        shuffleArray(hexResources);

        int count = 0;
        for (int i = 0; i <= 7; i++) {
            for (int x = 0; x <= 7; x++) {
                if (! hexes[x][i].getGhost()) {
                    hexes[x][i].setDiceRoll(diceRolls[count]);
                    hexes[x][i].setType(hexResources[count]);
                    count++;
                }
            }
        }
    }

    public static void populateStartingPositions() {
        startingPositions[0] = 122122;
        startingPositions[1] = 243435;
        startingPositions[2] = 223233;
        startingPositions[3] = 434454;
        startingPositions[4] = 233334;
        startingPositions[5] = 334243;
        startingPositions[6] = 324142;
        startingPositions[7] = 425253;
    }

    public static void initializeHouses() {
        //shuffleArray(startingPositions);
        for (int i = 0; i <= 1; i++) {
            int[] position = CatanOps.coordsOfVertex(startingPositions[i],hexes);
            House house = new House(3, position[0], position[1], false, red.getPlayerColor());
            houseStore.put(startingPositions[i],house);
        }
        for (int i = 2; i <= 3; i++) {
            int[] position = CatanOps.coordsOfVertex(startingPositions[i],hexes);
            House house = new House(3, position[0], position[1], false, blue.getPlayerColor());
            houseStore.put(startingPositions[i],house);
        }
        for (int i = 4; i <= 5; i++) {
            int[] position = CatanOps.coordsOfVertex(startingPositions[i],hexes);
            House house = new House(3, position[0], position[1], false, green.getPlayerColor());
            houseStore.put(startingPositions[i],house);
        }
        for (int i = 6; i <= 7; i++) {
            int[] position = CatanOps.coordsOfVertex(startingPositions[i],hexes);
            House house = new House(3, position[0], position[1], false, yellow.getPlayerColor());
            houseStore.put(startingPositions[i],house);
        }
    }

    public void checkWin(Graphics g) {
        if (red.getVP() >= 7) {
            setTalkBack(g,"Red Wins!");
        }
        if (blue.getVP() >= 7) {
            setTalkBack(g,"Blue Wins!");
        }
        if (yellow.getVP() >= 7) {
            setTalkBack(g,"Yellow Wins!");
        }
        if (green.getVP() >= 7) {
            setTalkBack(g,"Green Wins!");
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
        players[1] = green;
        players[2] = blue;
        players[3] = yellow;

        //to test resources
        //players[0].setRock(5);
        //players[1].setBrick(100);

    }

    //draw houses and cities
    public static void drawHomes(Graphics g, HashMap<Integer, House> store){
        //use iterator to iterate through hashmap of houses
        Iterator it = store.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Integer, House> entry = (Map.Entry)it.next();
            House home = entry.getValue();
            System.out.println("House is: " + home);
            int state = home.getState();
            System.out.println("State is: " + state);

            // change the Color
            setColor(g, home.getPlayerColor());

            //check what is contained at the vertex
            if (state == 0 || state == 1) {
                continue;
            }
            if (state == 2){
                int rad = 8;
                System.out.println("HomeX:" + home.getX());
                //g.fillOval(home.getX() - rad, home.getY() - rad, 2*rad, 2*rad);
                //new house draw
                g.fillRect(home.getX()-rad,home.getY()-rad,rad*2,rad*2);
                //draw roof
                //g.drawPolygon(new int[] {home.getX() - rad, home.getX() + rad, home.getX() + 2*rad},
                //new int[] {home.getY()-rad, home.getY()-rad, home.getY()-2*rad}, 3);
            }
            if (state == 3) {
                int rad = 6;
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

    public static void drawRoads(Graphics g, HashMap<Integer, Road> store){
        //use iterator to iterate through hashmap of houses
        Iterator it = store.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Integer, Road> entry = (Map.Entry)it.next();
            Road road = entry.getValue();
            int state = road.getState();

            // change the Color
            setColor(g, road.getPlayerColor());

            //check what is contained at the vertex
            if (state == 0 || state == 1) {
                continue;
            }
            if (state == 2){
                if (Math.abs(road.getX1()-road.getX2()) < 4) {
                    for (int i = -2; i <= 2; i++) {
                        g.drawLine(road.getX1() + i, road.getY1(), road.getX2() + i,
                                    road.getY2());
                    }
                }
                else {
                    for (int i = -2; i <= 2; i++) {
                        g.drawLine(road.getX1(), road.getY1() + i, road.getX2(),
                                    road.getY2() + i);
                    }
                }

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

        if (parent.whichButton == 0 || parent.whichButton == 4) {
            // do nothing

        } else if (parent.whichButton == 1) { // build CITY
            int color = 0; // set player color
            System.out.println("CITY");

            Hex[] nearest = CatanOps.nearestThreeHexes(x, y, hexes);
            int vertex = CatanOps.makeVertexID(nearest[0], nearest[1], nearest[2]);

            System.out.println("Vertex is: " + houseStore.get(vertex));
            boolean con = houseStore.containsKey(5);
            if (!con && canBuildCity(parent.currentPlayer) && !houseStore.get(vertex).getIsCity()
            && houseStore.get(vertex).getPlayerColor() == parent.currentPlayer.getPlayerColor() &&
            houseStore.get(vertex).getState() == 3) {

                    int avgX = (nearest[0].getX() + nearest[1].getX()
                                    +nearest[2].getX())/3;
                    int avgY = (nearest[0].getY() + nearest[1].getY()
                                    +nearest[2].getY())/3;
                    House city = new House(2, avgX, avgY, true, parent.currentPlayer.getPlayerColor());
                    houseStore.put(vertex, city);
                    assignOwed(nearest, true, city.getPlayerColor());
                    parent.currentPlayer.setVP(1);
                    parent.lc.repaint();
            } else setTalkBack(g,"You cant build a city here!");




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
            System.out.println("Length of array is " + nearest.length);
            int vertex = CatanOps.makeVertexID(nearest[0], nearest[1], nearest[2]);

            boolean con = houseStore.containsKey(vertex);

            if (!con && canBuildSettlement(parent.currentPlayer) && (turnType || containsRoadOfSameColorHouse(vertex,CatanOps.adjacentPathsToVertex(vertex,hexes)))
            && !containsTwoRoadsOfOtherColors(vertex,CatanOps.adjacentPathsToVertex(vertex,hexes))) {
                parent.currentPlayer.takeWood(1);
                parent.currentPlayer.takeBrick(1);
                parent.currentPlayer.takeSheep(1);
                parent.currentPlayer.takeWheat(1);
                int avgX = (nearest[0].getX() + nearest[1].getX()
                                +nearest[2].getX())/3;
                int avgY = (nearest[0].getY() + nearest[1].getY()
                                +nearest[2].getY())/3;
                System.out.println("AvgX= " + avgX);
                House settle = new House(3, avgX, avgY, false, parent.currentPlayer.getPlayerColor());
                houseStore.put(vertex, settle);

                assignOwed(nearest, false, parent.currentPlayer.getPlayerColor());
                parent.currentPlayer.setVP(1);
                System.out.println("Original Vertex: " + vertex);
                //house reservations
                int[] adjacents = CatanOps.adjacentVerticesToVertex(vertex,hexes);
                for (int i = 0; i < adjacents.length; i++) {
                    System.out.println("Vertex: " + adjacents[i]);
                    House resSettle = new House(1, avgX, avgY, false, parent.currentPlayer.getPlayerColor());
                    //System.out.println("I is:" + i);
                    houseStore.put(adjacents[i], resSettle);
                }



                    //reserves roads
            } else setTalkBack(g,"You cant build a settlement here!");

            this.repaint();
            parent.lc.repaint();




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
            System.out.print(nearest[0].getX() + " ");
            System.out.println(nearest[0].getY() + " ");
            System.out.print(nearest[1].getX() + " ");
            System.out.println(nearest[1].getY() + " ");
            int path = CatanOps.makePathID(nearest[0], nearest[1]);
            int[] vertices = CatanOps.adjacentVerticesToPath(path, hexes);
            System.out.println("PathID:" + path);
            System.out.println(vertices[0]);
            System.out.println(vertices[1]);

            boolean con = roadStore.containsKey(path);
            if (!con && canBuildRoad(parent.currentPlayer) && (containsRoadOfSameColor(path,CatanOps.adjacentPathsToPath(path,hexes)) ||
            containsHouseOfSameColor(path,CatanOps.adjacentVerticesToPath(path,hexes)))
            && !containsHouseOfOtherColor(path,CatanOps.adjacentVerticesToPath(path,hexes))) {

                    parent.currentPlayer.takeBrick(1);
                    parent.currentPlayer.takeWood(1);

                    //better road algorithm:
                    double x0 = nearest[0].getX();
                    double y0 = nearest[0].getY();
                    double x1 = nearest[1].getX();
                    double y1 = nearest[1].getY();
                    double midpointX = (x0 + x1)/2;
                    double midpointY = (y0 + y1)/2;
                    //System.out.println(midpointX);
                    //System.out.println(midpointY);
                    double slope = ((y1-y0)/(x1-x0));
                    double reverseSlope = (1/slope);
                    double degreeAngle = Math.atan(reverseSlope);

                    int offSetX = (int)(hexSize*Math.cos(degreeAngle))/2;
                    int offSetY = (int)(hexSize*Math.sin(degreeAngle))/2;
                        int xx1 = (int)(midpointX + offSetX);
                        int yy1 = (int)(midpointY - offSetY);
                        int xx2 = (int)(midpointX - offSetX);
                        int yy2 = (int)(midpointY + offSetY);
                    Road road = new Road(2, xx1, yy1, xx2, yy2,parent.currentPlayer.getPlayerColor());
                    roadStore.put(path, road);


            } else setTalkBack(g,"You cant build a road here!");



            this.repaint();
            parent.lc.repaint();
            // TODO: Add calls to CatanOpps
            // checks for adjacent roads of that player
                // same int value
            // if they are the same
                // add int value of player to the RodeStore using a new
                //road object
            // else print error message!

        }

    }

    public void setTurnType(boolean type) {
        this.turnType = type;
    }

    public boolean getTurnType() {
        return this.turnType;
    }

    //returns true if the adjacent paths to a vertex have two paths of other colors
    public boolean containsTwoRoadsOfOtherColors(int vertex, int[] paths) {
        int redC = 0;
        int greenC = 0;
        int yellowC = 0;
        int blueC = 0;
        for (int i = 0; i <= 2; i++) {
            if (roadStore.containsKey(paths[i])) {
                if (roadStore.get(paths[i]).getPlayerColor() != parent.currentPlayer.getPlayerColor()) {
                    if (roadStore.get(paths[i]).getPlayerColor() == 0) {
                        redC++;
                    }
                    if (roadStore.get(paths[i]).getPlayerColor() == 1) {
                        greenC++;
                    }
                    if (roadStore.get(paths[i]).getPlayerColor() == 2) {
                        yellowC++;
                    }
                    if (roadStore.get(paths[i]).getPlayerColor() == 3) {
                        blueC++;
                    }
                }
            }
        }
        return (redC >= 2 || greenC >= 2 || yellowC >= 2 || blueC >= 2);
    }

    //returns true if an adjacent vertex to a path contains an opposing house
    public boolean containsHouseOfOtherColor(int path, int[] vertices) {
        for (int i = 0; i <= 1; i++) {
            if (houseStore.containsKey(vertices[i])) {
                if (houseStore.get(vertices[i]).getPlayerColor() != parent.currentPlayer.getPlayerColor()) {
                    return true;
                }
            }
        }
        return false;
    }

    //checks if the surrounding paths of a path contain a road of same color
    public boolean containsRoadOfSameColor(int path, int[] paths) {
        for (int i = 0; i <= 3; i++) {
            if (roadStore.containsKey(paths[i])) {
                if (roadStore.get(paths[i]).getPlayerColor() == parent.currentPlayer.getPlayerColor()) {
                    return true;
                }
            }
        }
        return false;
    }

    //checks if the surrounding paths of a vertex contain a road of same color
    public boolean containsRoadOfSameColorHouse(int vertex, int[] paths) {
        for (int i = 0; i <= 2; i++) {
            if (roadStore.containsKey(paths[i])) {
                if (roadStore.get(paths[i]).getPlayerColor() == parent.currentPlayer.getPlayerColor()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsHouseOfSameColor(int path, int[] vertices) {
        for (int i = 0; i <= 1; i++) {
            if (houseStore.containsKey(vertices[i])) {
                if (houseStore.get(vertices[i]).getPlayerColor() == parent.currentPlayer.getPlayerColor() &&
                houseStore.get(vertices[i]).getState() != 1) {
                    return true;
                }
            }
        }
        return false;
    }

    //activates the console
    public void setTalkBack(Graphics g, String s) {
        Dimension d = getSize();
        talkBack = s;
    }

    public static boolean canBuildSettlement(Player p) {
        return (p.getWheat() >= 1 && p.getWood() >= 1 && p.getBrick() >= 1 &&
        p.getSheep() >= 1);
    }

    public static boolean canBuildCity(Player p) {
        return (p.getRock() >= 3 && p.getWheat() <= 2);
    }

    public static boolean canBuildRoad(Player p) {
        return (p.getWood() >= 1 && p.getBrick() <= 1);
    }

    public void assignOwed(Hex[] nearest, boolean isCity, int player){
        for (int i=0; i<3 ; i++ ) {
            Hex hex = nearest[i];
            if (isCity)
                hex.setOwed(player, 2);
            else hex.setOwed(player, 1);
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
