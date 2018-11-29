// An Applet for the board game Catan
//
//CS201 final project - Catan

import java.awt.*;        // abstract window toolkit
import java.awt.event.*;  // event handling
import java.applet.*;     // Applet classes

@SuppressWarnings("serial") // to avoid Eclipse warning
public class CatanApplet extends Applet implements ActionListener
{
    // instance variables

    private Button endButton, cityButton, settlementButton, roadButton;
    private BoardCanvas bc;   // shows points and lines in 2D plane

    // initialize applet
    public void init() {  // layout of applet

        // ADD BACK IN
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
        //gamePanel.add(test);
        gamePanel.add(westMidPanel());

        setLayout(new BorderLayout());
        add("North", title);
        add("Center", gamePanel);
    }

    // create panel with buttons and menu
    private Panel westMidPanel() {
        cityButton = new Button("CITY");
        cityButton.addActionListener(this);
        settlementButton = new Button("SETTLEMENT");
        settlementButton.addActionListener(this);
        roadButton = new Button("ROAD");
        roadButton.addActionListener(this);


        endButton = new Button("END TURN");
        endButton.addActionListener(this);

        Panel buildButtons = new Panel();
        buildButtons.setLayout(new FlowLayout());
        buildButtons.add(cityButton);
        buildButtons.add(settlementButton);
        buildButtons.add(roadButton);

        Panel endPanel = new Panel();
        endPanel.setLayout(new FlowLayout());
        endPanel.add(endButton);

        Panel p = new Panel();
        p.setLayout(new BorderLayout());
        p.add("Center", buildButtons);
        p.add("South", endPanel);

        return p;
    }


    // action handler for buttons
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == endButton) {
            // TODO: Add calls to CatanOpps
        } else if (evt.getSource() == cityButton) {
            // TODO: Add calls to CatanOpps

        } else if (evt.getSource() == cityButton) {
            // TODO: Add calls to CatanOpps

        } else if (evt.getSource() == cityButton) {
            // TODO: Add calls to CatanOpps

        }
    }
}
