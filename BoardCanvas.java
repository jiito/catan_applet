// BoardCanvas:  an object that displays a the Catan Board
//
// CS 201 Exam 2

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("serial") // to avoid Eclipse warning
public class BoardCanvas extends Canvas {

    // instance variables

    // TODO: Add data structures here

    Hex hexes[][] = new Hex[5][5];


    // HERE WILL

    // RIGHT HEREEEEEEEEEEE

    protected CatanApplet parent;  // access to main applet class

    // fonts used:
    static final Font textFont = new Font("Arial", Font.PLAIN, 14);
    static final Font nodeFont = new Font("Arial", Font.PLAIN, 10);
    static final Font nodeFontBold = new Font("Arial", Font.BOLD, 11);

    // constructor
    public BoardCanvas(CatanApplet app) {
        parent = app;
        // ADD new arrays here
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
            drawHex(g, centerX, centerY, size);
            Hex hex = new Hex(0, centerX, centerY, 0, i, 6);// change to be random
            hexes[0][i] = hex;
            centerX += w;
        }
        centerX-=w/2;
        centerY += .75 * h;
        for (int i = 0; i<4 ; i++ ) { // second row of hexs
            drawHex(g, centerX, centerY, size);
            Hex hex = new Hex(0, centerX, centerY, 1, i, 6);// change to be random
            hexes[1][i] = hex;
            centerX -= w;
        }
        for (int i =0; i<3; i++){ // test loop
            System.out.println("X is: " + hexes[0][i].getCol());
        }
        centerX+=w/2;
        centerY += .75 * h;
        for (int i = 0; i<5 ; i++ ) { // third row of hexs
            drawHex(g, centerX, centerY, size);
            Hex hex = new Hex(0, centerX, centerY, 2, i, 6);// change to be random
            hexes[2][i] = hex;
            centerX += w;
        }
        centerX-=3* w/2;
        centerY += .75 * h;
        for (int i = 0; i<4 ; i++ ) { // fourth row of hexs
            drawHex(g, centerX, centerY, size);
            Hex hex = new Hex(0, centerX, centerY, 3, i, 6);// change to be random
            hexes[3][i] = hex;
            centerX -= w;
        }
        centerX+=3* w/2;
        centerY += .75 * h;
        for (int i = 0; i<3 ; i++ ) { // fifth row of hexs
            drawHex(g, centerX, centerY, size);
            Hex hex = new Hex(0, centerX, centerY, 4, i, 6); // change to be random
            hexes[4][i] = hex;
            centerX += w;
        }
        for (int i =0; i<hexes.length; i++){ // test loop
            for (int p = 0; i< hexes[i].length; p++) {
                System.out.println("X is: " + hexes[i][p].getCol());
            }
        }

        int diceRoll1 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        int diceRoll2 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        String compRoll = Integer.toString(diceRoll1 + diceRoll2);

        String diceRoll = "Dice roll: " + compRoll; // comp roll will have to specified outside
        centerString(g, diceRoll, (int)d.getWidth()/2, 12*size);

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
    // draw a String centered at x, y
    public static void centerString(Graphics g, String s, int x, int y) {
        FontMetrics fm = g.getFontMetrics(g.getFont());
        int xs = x - fm.stringWidth(s)/2;
        int ys = y + fm.getAscent()/3;
        g.drawString(s, xs, ys);
    }
}
