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

    //stores an even distribution of hex resource types.
    public static int[] hexResources = new int[19];

    //stores an even distribution of all diceRolls.
    public static int[] diceRolls = new int[19];

    //stores all players in an array.
    public static Player[] players = new Player[4];

        //declares player objects.
        public static Player red = new Player(0);
        public static Player blue = new Player(1);
        public static Player green = new Player(2);
        public static Player orange = new Player(3);

    private Button endButton, cityButton, settlementButton, roadButton;
    private BoardCanvas bc;   // shows points and lines in 2D plane

    protected int whichButton;


    //

    // initialize applet
    public void init() {  // layout of applet

        //initialize data structures:

            //populates hexResources
            populateHexResources();

            //populates diceRolls
            populateDiceRolls();

            //populates players
            populatePlayers();

        // set which button to "null" state


        bc = new BoardCanvas(this, roadStore, houseStore, players);
        bc.setBackground(Color.white);

        Label test = new Label("TEST CANVAS");
        test.setAlignment(Label.CENTER);
        test.setBackground(Color.blue);
        test.setForeground(Color.white);

        Label title = new Label("Settlers of Catan");
        title.setAlignment(Label.CENTER);
        title.setBackground(Color.blue);
        title.setForeground(Color.white);

        Panel gamePanel = new Panel();
        gamePanel.setLayout(new GridLayout(1,2,2,2));
        gamePanel.setBackground(new Color(240, 240, 255));
        gamePanel.add(bc); // ADD BACK IN AFTER WRITING CLASS
        gamePanel.add(test);


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
    private void populatePlayers() {

        players[0] = red;
        players[1] = blue;
        players[2] = green;
        players[3] = orange;
    }


    // getters
    public int getButton(){
        return this.whichButton;
    }
}
