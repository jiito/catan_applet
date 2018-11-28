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




    protected CantanApplet parent;  // access to main applet class

    protected int drawStyle;  // how tree is drawn (0 - fixed, 1 - variable)

    // fonts used:
    static final Font textFont = new Font("Arial", Font.PLAIN, 14);
    static final Font nodeFont = new Font("Arial", Font.PLAIN, 10);
    static final Font nodeFontBold = new Font("Arial", Font.BOLD, 11);

    // constructor
    public TreeCanvas(KdTreeApplet app) {
        parent = app;
        drawStyle = 0;
    }

    // instance methods

    // set draw style
    public void setDrawStyle(int n) {
        drawStyle = n;
        repaint();
    }

    // repaint this canvas
    public void paint (Graphics g) {

        // turn on anti-aliasing for smoother lines
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        setLineWidth(g, 2);

        Dimension d = getSize();        // size of canvas
        KdTree t = parent.tree();

        // print info about tree at the top
        int xnodes = KdTreeOps.countNodes(t, true);
        int ynodes = KdTreeOps.countNodes(t, false);
        String s1 = "X nodes: " + xnodes + "       Y nodes: " + ynodes;
        String s2 = (KdTreeOps.isSymmetric(t) ? "Symmetric" : "NOT Symmetric");
        g.setColor(Color.black);
        g.setFont(textFont);
        centerString(g, s1, d.width/2, 12);
        centerString(g, s2, d.width/2, 28);

        // draw tree
        int radius = 18;
        int dy = 45;
        int y0 = 57;
        if (drawStyle == 0) { // draw with fixed width
            int l = radius;
            int r = d.width-radius;
            drawTreeFixed(g, t, 0, l, r, y0, dy, radius);
        } else { // draw with variable width
            int offset = KdTreeOps.width(t)/2 - KdTreeOps.leftWidth(t);
            int x =  d.width/2 - radius * offset;
            drawTreeVariable(g, t, 0, x, y0, dy, radius);
        }
    }

    // FIXED WIDTH DRAWING:
    // Draws the tree t at height y with root centered between l and r.
    // The next level is drawn at height y+dy.
    // Each leaf is drawn as a circle with radius rad.
    public static void drawTreeFixed(Graphics g, KdTree t, int level,
            int l, int r, int y, int dy, int rad) {

        int x = (l+r)/2;
        g.setColor(Color.black);
        if (t == null)
            g.fillOval(x-3, y-3, 7, 7);
        else {
            int y2 = y+dy;
            if (t.left() != null) {
                g.setColor(Color.black);
                g.drawLine(x, y, (l+x)/2, y2);
                drawTreeFixed(g, t.left(), level+1, l, x, y2, dy, rad);
            }
            if (t.right() != null) {
                g.setColor(Color.black);
                g.drawLine(x, y, (x+r)/2, y2);
                drawTreeFixed(g, t.right(), level+1, x, r, y2, dy, rad);
            }
            drawNode(g, t, level, x, y, rad);
        }
    }

    // VARIABLE WIDTH DRAWING:
    // Draws the tree t centered around x with its root at height y.
    // The next level is drawn at height y+dy.
    // Each leaf is drawn as a circle with radius rad.
    // Subtrees are separated no more space than necessary.
    public static void drawTreeVariable(Graphics g, KdTree t, int level,
            int x, int y, int dy, int rad) {

        g.setColor(Color.black);
        if (t == null)
            g.fillOval(x-3, y-3, 7, 7);
        else {
            int y2 = y+dy;
            if (t.left() != null) {
                int x2 = x - rad * KdTreeOps.rightWidth(t.left());
                g.setColor(Color.black);
                g.drawLine(x, y, x2, y2);
                drawTreeVariable(g, t.left(), level+1, x2, y2, dy, rad);
            }
            if (t.right() != null) {
                int x2 = x + rad * KdTreeOps.leftWidth(t.right());
                g.setColor(Color.black);
                g.drawLine(x, y, x2, y2);
                drawTreeVariable(g, t.right(), level+1, x2, y2, dy, rad);
            }
            drawNode(g, t, level, x, y, rad);
        }
    }

    // Draws root node of t using color based on level and its type
    public static void drawNode(Graphics g, KdTree t, int level,
            int x, int y, int rad) {

        setNodeColor(g, level, t.isX());
        g.fillOval(x-rad, y-rad, 2*rad, 2*rad);
        g.setColor(Color.black);
        g.drawOval(x-rad, y-rad, 2*rad, 2*rad);

        Point p = t.point();
        setTextColor(g, level, t.isX());
        g.setFont(t.isX() ? nodeFontBold : nodeFont);
        centerString(g, "x:" + p.x, x, y-4);
        g.setFont(!t.isX() ? nodeFontBold : nodeFont);
        centerString(g, "y:" + p.y, x, y+6);
    }

    // set node's color based on x (blue) or y (red)
    // the deeper in the tree, the lighter
    public static void setNodeColor(Graphics g, int level, boolean isX) {
        float hue = (isX ? 0.6666f : 0.0f); // blue for x, red for y
        float saturation = (float)Math.max(0.1,  Math.min(1.0,  (12-level)/8.0));
        float brightness = (float)Math.max(0.3,  Math.min(1.0,  (level+2)/6.0));
        //System.out.printf("lev=%d sat=%.2f bri=%.2f\n", level, saturation, brightness);
        g.setColor(Color.getHSBColor(hue, saturation, brightness));
    }

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

    // set the line width
    public static void setLineWidth(Graphics g, int lineWidth) {
        lineWidth = Math.max(1, lineWidth);
        ((Graphics2D)g).setStroke(new BasicStroke(lineWidth));
    }
}
