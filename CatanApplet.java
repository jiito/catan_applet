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
    private LabelsCanvas l;

    // initialize applet
    public void init() {  // layout of applet

        // ADD BACK IN
        bc = new BoardCanvas(this);
        bc.setBackground(Color.white);
        //bc.addMouseListener(bc);

        Image bricks = getImage(getDocumentBase(), "brick.png");
        Image ore = getImage(getDocumentBase(), "ore.png");
        l = new LabelsCanvas(this, bricks, ore);
        l.setBackground(Color.white);
        //l.addMouseListener(l);

        // Label test = new Label("TEST CANVAS");
        // test.setAlignment(Label.CENTER);
        // test.setBackground(Color.blue);
        // test.setForeground(Color.white);

        Label title = new Label("Settlers of Catan");
        title.setAlignment(Label.CENTER);
        title.setBackground(Color.blue);
        title.setForeground(Color.white);

        Panel gamePanel = new Panel();
        gamePanel.setLayout(new GridLayout(1,2,2,2));
        gamePanel.setBackground(new Color(240, 240, 255));
        gamePanel.add(bc); // ADD BACK IN AFTER WRITING CLASS
        gamePanel.add(l);


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
        } else if (evt.getSource() == cityButton) {
            // TODO: Add calls to CatanOpps
            //steps:
            // append to array
            // repaint

        } else if (evt.getSource() == settlementButton) {
            // TODO: Add calls to CatanOpps
            //steps:
            // append to vertex array
            // repaint

        } else if (evt.getSource() == roadButton) {
            // TODO: Add calls to CatanOpps
            // steps:
            // change array
            // repaint()

        }
    }
}
