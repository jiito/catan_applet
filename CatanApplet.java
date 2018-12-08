// An Applet for the board game Catan
//
//CS201 final project - Catan

import java.awt.*;        // abstract window toolkit
import java.awt.event.*;  // event handling
import java.applet.*;     // Applet classes
import java.util.*;

@SuppressWarnings("serial") // to avoid Eclipse warning
public class CatanApplet extends Applet implements ActionListener
{
    // instance variables

    //stores the road information
    public static HashMap roadStore = new HashMap(72);

    //stores the house information
    public static HashMap houseStore = new HashMap(54);

    public int whichButton;
    public int turn;
    public Player currentPlayer;
    public int diceRoll;
    public Image key;

    // stores the player objects of the game
    public Player[] players;

    public Button endButton, cityButton, settlementButton, roadButton;
    public BoardCanvas bc;   // shows game board
    public LabelsCanvas lc;   // shows dynamic labels



    // stores hexes in array
    public static Hex hexes[][] = new Hex[8][8];

    // color instance vars (catan theme)
    public static Color deepRed =  new Color(194, 68, 34);
    public static Color gold =  new Color(244, 191, 15);
    public static Color cYellow =  new Color(242, 225, 131);
    public static Color cOrange =  new Color(242, 117, 39);

    // set font for title
    static final Font titleFont = new Font("Arial", Font.PLAIN, 20);


    // initialize applet
    public void init() {  // layout of applet




        // build canvas to diplay game board
        bc = new BoardCanvas(this, roadStore, houseStore, key);
        bc.setBackground(Color.white);
        bc.addMouseListener(bc);
        bc.addMouseMotionListener(bc);

        // import images
        Image key = getImage(getDocumentBase(), "key.png");
        Image wood = getImage(getDocumentBase(), "wood.png");
        Image sheep = getImage(getDocumentBase(), "sheep.png");
        Image wheat = getImage(getDocumentBase(), "wheat.png");
        Image ore = getImage(getDocumentBase(), "rock.png");
        Image bricks = getImage(getDocumentBase(), "brick.png");
        Image buc = getImage(getDocumentBase(), "build_card.png");

        // create lable canvas
        lc = new LabelsCanvas(this, bricks, ore, wood, sheep, wheat, buc);
        lc.setBackground(cYellow);

        // Create title
        Label title = new Label("Settlers of Catan");
        title.setAlignment(Label.CENTER);
        title.setBackground(deepRed);
        title.setForeground(gold);
        title.setFont(titleFont);

        // create game panel w/ the two canvases
        Panel gamePanel = new Panel();
        gamePanel.setLayout(new GridLayout(1,2,2,2));
        gamePanel.setBackground(new Color(240, 240, 255));
        gamePanel.add(bc);
        gamePanel.add(lc);

        // set the overall layout of the applet
        setLayout(new BorderLayout());
        add("North", title);
        add("Center", gamePanel);
        add("South", southPanel());
    }


    // create panel with buttons and menu
    private Panel southPanel() {
        cityButton = new Button("CITY");
        cityButton.addActionListener(this);
        settlementButton = new Button("SETTLEMENT");
        settlementButton.addActionListener(this);
        roadButton = new Button("ROAD");
        roadButton.addActionListener(this);
        endButton = new Button("END TURN");
        endButton.addActionListener(this);

        // add buttons to panel
        Panel buttons = new Panel();
        buttons.setLayout(new FlowLayout());
        buttons.setBackground(deepRed);
        buttons.add(cityButton);
        buttons.add(settlementButton);
        buttons.add(roadButton);
        buttons.add(endButton);

        return buttons;
    }

    public int j = 0;

    // action handler for buttons
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == endButton) {
            // get current player
            int i = Arrays.asList(this.players).indexOf(this.currentPlayer);

            // special statements to control specific start to the game
            // preforms snake like term structre for first two rounds
            if (j == 3) {
            }
            else if (j > 3 && j < 7) {
                i--;
            }
            else {
                i++;
            }
            j++;
            this.turn = j;

            // swritch to the next player
            this.currentPlayer = this.players[(i+4)%4];// attempt to make it circular

            // give players initial resources
            if (j < 8) {
                bc.setTurnType(true);
                this.currentPlayer.setWood(2);
                this.currentPlayer.setWheat(1);
                this.currentPlayer.setBrick(2);
                this.currentPlayer.setSheep(1);
            }
            else {
                bc.setTurnType(false);
            }

            // repaint the canvases
            bc.repaint();
            lc.repaint();

            // set whichButton to the endbutton
            this.whichButton = 4;

        // other calls to other buttons that just set the instance var for
        // board canvas to interpret
        } else if (evt.getSource() == cityButton) {
            this.whichButton = 1;
            System.out.println("City button pressed");
        } else if (evt.getSource() == settlementButton) {
            this.whichButton = 2;
            System.out.println("Seetlement button pressed");
        } else if (evt.getSource() == roadButton) {
            this.whichButton =3;
            System.out.println("Road button pressed");
        }
    }



}
