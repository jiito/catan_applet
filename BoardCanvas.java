// BoardCanvas:  an object that displays a the Catan Board
//
// CS 201 Exam 2

import java.awt.*;

@SuppressWarnings("serial") // to avoid Eclipse warning
public class BoardCanvas extends Canvas {

    // instance variables

    // TODO: Add data structures here


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
            centerX += w;
        }
        centerX-=w/2;
        centerY += .75 * h;
        for (int i = 3; i<7 ; i++ ) { // second row of hexs
            drawHex(g, centerX, centerY, size);
            centerX -= w;
        }
        centerX+=w/2;
        centerY += .75 * h;
        for (int i = 7; i<12 ; i++ ) { // third row of hexs
            drawHex(g, centerX, centerY, size);
            centerX += w;
        }
        centerX-=3* w/2;
        centerY += .75 * h;
        for (int i = 12; i<16 ; i++ ) { // fourth row of hexs
            drawHex(g, centerX, centerY, size);
            centerX -= w;
        }
        centerX+=3* w/2;
        centerY += .75 * h;
        for (int i = 16; i<19 ; i++ ) { // fifth row of hexs
            drawHex(g, centerX, centerY, size);
            centerX += w;
        }


    }
    public static void drawHex(Graphics g, int centerX, int centerY,
                                int size){
        for (int i = 1; i<=6 ; i++) {
            System.out.println("I is: " + i);
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
}

    // VARIABLE WIDTH DRAWING:
    // Draws the tree t centered around x with its root at height y.
    // The next level is drawn at height y+dy.
    // Each leaf is drawn as a circle with radius rad.
    // Subtrees are separated no more space than necessary.
    // public static void drawTreeVariable(Graphics g, KdTree t, int level,
    //         int x, int y, int dy, int rad) {
    //
    //     g.setColor(Color.black);
    //     if (t == null)
    //         g.fillOval(x-3, y-3, 7, 7);
    //     else {
    //         int y2 = y+dy;
    //         if (t.left() != null) {
    //             int x2 = x - rad * KdTreeOps.rightWidth(t.left());
    //             g.setColor(Color.black);
    //             g.drawLine(x, y, x2, y2);
    //             drawTreeVariable(g, t.left(), level+1, x2, y2, dy, rad);
    //         }
    //         if (t.right() != null) {
    //             int x2 = x + rad * KdTreeOps.leftWidth(t.right());
    //             g.setColor(Color.black);
    //             g.drawLine(x, y, x2, y2);
    //             drawTreeVariable(g, t.right(), level+1, x2, y2, dy, rad);
    //         }
    //         drawNode(g, t, level, x, y, rad);
    //     }
    // }
    //
    // // Draws root node of t using color based on level and its type
    // public static void drawNode(Graphics g, KdTree t, int level,
    //         int x, int y, int rad) {
    //
    //     setNodeColor(g, level, t.isX());
    //     g.fillOval(x-rad, y-rad, 2*rad, 2*rad);
    //     g.setColor(Color.black);
    //     g.drawOval(x-rad, y-rad, 2*rad, 2*rad);
    //
    //     Point p = t.point();
    //     setTextColor(g, level, t.isX());
    //     g.setFont(t.isX() ? nodeFontBold : nodeFont);
    //     centerString(g, "x:" + p.x, x, y-4);
    //     g.setFont(!t.isX() ? nodeFontBold : nodeFont);
    //     centerString(g, "y:" + p.y, x, y+6);
    // }
    //
    // // set node's color based on x (blue) or y (red)
    // // the deeper in the tree, the lighter
    // public static void setNodeColor(Graphics g, int level, boolean isX) {
    //     float hue = (isX ? 0.6666f : 0.0f); // blue for x, red for y
    //     float saturation = (float)Math.max(0.1,  Math.min(1.0,  (12-level)/8.0));
    //     float brightness = (float)Math.max(0.3,  Math.min(1.0,  (level+2)/6.0));
    //     //System.out.printf("lev=%d sat=%.2f bri=%.2f\n", level, saturation, brightness);
    //     g.setColor(Color.getHSBColor(hue, saturation, brightness));
    // }
    //
    // // set node's text color (black or white) so that better contrast
    // public static void setTextColor(Graphics g, int level, boolean isX) {
    //     if (isX && level < 10 || level < 4)
    //         g.setColor(Color.white);
    //     else
    //         g.setColor(Color.black);
    // }
    //
    // // draw a String centered at x, y
    // public static void centerString(Graphics g, String s, int x, int y) {
    //     FontMetrics fm = g.getFontMetrics(g.getFont());
    //     int xs = x - fm.stringWidth(s)/2;
    //     int ys = y + fm.getAscent()/3;
    //     g.drawString(s, xs, ys);
    // }
    //
    // // set the line width
    // public static void setLineWidth(Graphics g, int lineWidth) {
    //     lineWidth = Math.max(1, lineWidth);
    //     ((Graphics2D)g).setStroke(new BasicStroke(lineWidth));
    // }
