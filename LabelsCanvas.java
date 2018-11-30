// LabelsCanvas:  an object that displays a the dynamic labels
//
// CS 201 Exam 2

import java.awt.*;

@SuppressWarnings("serial") // to avoid Eclipse warning
public class LabelsCanvas extends Canvas {

    protected CatanApplet parent;  // access to main applet class

    static final Font textFont = new Font("Arial", Font.PLAIN, 14);
    static final Font nodeFont = new Font("Arial", Font.PLAIN, 10);
    static final Font nodeFontBold = new Font("Arial", Font.BOLD, 11);

    Image bricks, ore;
    //constructor

    public LabelsCanvas(CatanApplet app, Image b, Image o) {
        parent = app;
        bricks = b;
        ore = o;
    }

    // repaint this canvas
    public void paint (Graphics g) {

        // turn on anti-aliasing for smoother lines
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        Dimension d = getSize();        // size of canvas

        // print info about tree at the top
        // COUNT RESOURCES
        String brickCount = "4";
        String oreCount = "1";

        String s1 = "Resources:";
        g.setColor(Color.black);
        g.setFont(textFont);
        centerString(g, s1, d.width/2, 12);
        g.drawImage(bricks, d.width/5, 28, this);
        g.drawImage(ore, 2* d.width/5, 28, this);
        centerString(g, brickCount, d.width/5 , 80);
        centerString(g, oreCount, 2* d.width/5 , 80);



    }

    //helper methods

    // set node's text color (black or white) so that better contrast
    public static void setTextColor(Graphics g, int level, boolean isX) {
        if (isX && level < 10 || level < 4)
            g.setColor(Color.white);
        else
            g.setColor(Color.black);
    }

    // draw a String centered at x, y
    public static void centerString(Graphics g, String s, int x, int y) {
        FontMetrics fm = g.getFontMetrics(g.getFont());
        int xs = x - fm.stringWidth(s)/2;
        int ys = y + fm.getAscent()/3;
        g.drawString(s, xs, ys);
    }

}
