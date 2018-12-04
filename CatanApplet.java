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

    public Button endButton, cityButton, settlementButton, roadButton;
    private BoardCanvas bc;   // shows points and lines in 2D plane
    private LabelsCanvas lc;

    protected int whichButton;

    public Player[] players;

    public Player currentPlayer;
    public int diceRoll;
    public static Hex hexes[][] = new Hex[5][5];
    //

    // initialize applet
    public void init() {  // layout of applet

        // set which button to "null" state


        bc = new BoardCanvas(this, roadStore, houseStore);
        bc.setBackground(Color.white);

        Image ore = getImage(getDocumentBase(), "ore.png");
        Image bricks = getImage(getDocumentBase(), "brick.png");
        lc = new LabelsCanvas(this, bricks, ore);
        lc.setBackground(Color.white);

        // Label test = new Label("TEST CANVAS");
        // test.setAlignment(Label.CENTER);
        //
        // test.setForeground(Color.white);

        Label title = new Label("Settlers of Catan");
        title.setAlignment(Label.CENTER);
        title.setBackground(Color.blue);
        title.setForeground(Color.white);

        Panel gamePanel = new Panel();
        gamePanel.setLayout(new GridLayout(1,2,2,2));
        gamePanel.setBackground(new Color(240, 240, 255));
        gamePanel.add(bc); // ADD BACK IN AFTER WRITING CLASS
        gamePanel.add(lc);


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

        Panel buttons = new Panel();
        buttons.setLayout(new FlowLayout());
        buttons.add(cityButton);
        buttons.add(settlementButton);
        buttons.add(roadButton);
        buttons.add(endButton);

        return buttons;
    }



    // action handler for buttons
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == endButton) {
            // TODO: Add calls to CatanOpps
            // switched player object?
            System.out.println("End turn button pressed");
            int i = Arrays.asList(this.players).indexOf(this.currentPlayer);
            i++;
            this.currentPlayer = players[(i+4)%4];// attempt to make it circular
            bc.repaint();
            lc.repaint();


        } else if (evt.getSource() == cityButton) {
            this.whichButton = 1;
            System.out.println("City button pressed");
            // add call to function in board canvas
        } else if (evt.getSource() == settlementButton) {
            this.whichButton = 2;
            System.out.println("Seetlement button pressed");
            // add call to function in board canvas
        } else if (evt.getSource() == roadButton) {
            this.whichButton =3;
            System.out.println("Road button pressed");
            // add call to function in board canvas
        }
    }



}
