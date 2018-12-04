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

    private Button endButton, cityButton, settlementButton, roadButton;
    private BoardCanvas bc;   // shows points and lines in 2D plane

    protected int whichButton;

    //

    // initialize applet
    public void init() {  // layout of applet

        // set which button to "null" state
        this.whichButton =0;

        bc = new BoardCanvas(this);
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

    private void populateHexResources() {
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

}
