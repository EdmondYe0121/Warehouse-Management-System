package ui;

import event.Event;
import event.EventLog;
import model.Cloth;
import model.Wardrobe;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// Represents application's main window frame.

public class WardrobeUI extends JFrame implements WindowListener, WindowFocusListener, WindowStateListener {
    private JTextField brand;
    private JTextField type;
    private JTextField colour;
    private JTextField gender;
    private JTextField size;
    private Wardrobe w1;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/wardrobe.json";

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;


    // Constructor with buttons, labels, textFields and visual window.
    public WardrobeUI() {
        super("Wardrobe Manager");
        initialize();
        createWardrobe();
        createRemove();
        createView();
        createViewCapacity();
        createSave();
        createLoad();
        createLabels();
        createAddLabels();
        createAddCommand();
        setVisible(true);
        addWindowListener(this);
        addWindowFocusListener(this);
        addWindowStateListener(this);

    }

    private void createAddLabels() {
        add(new JLabel("Brand"));
        add(brand);
        add(new JLabel("Type"));
        add(type);
        add(new JLabel("Colour"));
        add(colour);
        add(new JLabel("Gender(enter true for men)"));
        add(gender);
        add(new JLabel("Size(enter true for adults)"));
        add(size);
    }

    // initialization
    private void initialize() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridLayout(9,2));
    }

    // create add command with button
    private void createAddCommand() {
        Panel p1 = new Panel();
        p1.setLayout(new FlowLayout());
        Button addButton = new Button("add");
        addButton.setActionCommand("Add Cloth");
        addButton.addActionListener(this::addClothCommand);
        p1.add(new JLabel("Add Cloth"));
        p1.add(addButton);

        add(p1,BorderLayout.SOUTH);

    }

    // create new wardrobe
    private void createWardrobe() {
        Panel p2 = new Panel();
        p2.setLayout(new FlowLayout());
//        p2.add(new JLabel("Wardrobe Name"));
//        p2.add(new JTextField(10));
//        p2.add(new JLabel("Wardrobe Capacity"));
//        p2.add(new JTextField(10));
        Button enter = new Button("Create Wardrobe");
        enter.setActionCommand("Create Wardrobe");
        enter.addActionListener(this::wardrobeCommand);
        p2.add(enter);
        add(p2,BorderLayout.PAGE_START);
    }

    // create remove command
    private void createRemove() {
        Panel p3 = new Panel();
        p3.setLayout(new FlowLayout());
        Button remove = new Button("Remove Cloth");
        remove.setActionCommand("Remove Cloth");
        remove.addActionListener(this::removeClothCommand);
        p3.add(remove);
        add(p3,BorderLayout.AFTER_LAST_LINE);
    }

    // create view command
    private void createView() {
        Panel p4 = new Panel();
        Button viewClothes = new Button("View Clothes");
        viewClothes.setActionCommand("View Clothes");
        viewClothes.addActionListener(this::viewCommand);
        p4.add(viewClothes);
        add(p4);
    }

    // create view capacity command
    private void createViewCapacity() {
        Panel p5 = new Panel();
        Button viewCapcity = new Button("View Remaining Capacity");
        viewCapcity.setActionCommand("View Capacity");
        viewCapcity.addActionListener(this::viewCapacityCommand);
        p5.add(viewCapcity);
        add(p5);
    }

    // create save command
    private void createSave() {
        Panel p6 = new Panel();
        Button save = new Button("Save");
        save.setActionCommand("Save");
        save.addActionListener(this::saveCommand);
        p6.add(save);
        add(p6);
    }

    // create load command
    private void createLoad() {
        Panel p7 = new Panel();
        Button load = new Button("Load");
        load.setActionCommand("Load");
        load.addActionListener(this::loadCommand);
        p7.add(load);
        add(p7);
    }

    // set labels
    public void createLabels() {
        brand = new JTextField(10);
        type = new JTextField(10);
        colour = new JTextField(10);
        gender = new JTextField(10);
        size = new JTextField(10);
    }

    // perform wardrobe command
    public void wardrobeCommand(ActionEvent e) {
        if (e.getActionCommand().equals("Create Wardrobe")) {
            String name = JOptionPane.showInputDialog(null,
                    "Wardrobe Name?",
                    "Enter wardrobe name",
                    JOptionPane.QUESTION_MESSAGE);

            String capacity = JOptionPane.showInputDialog(null,
                    "Wardrobe Capacity?",
                    "Enter wardrobe capacity",
                    JOptionPane.QUESTION_MESSAGE);

            int capacityInt = Integer.valueOf(capacity);
            w1 = new Wardrobe(name,capacityInt);
        }
    }

    // perform add command
    public void addClothCommand(ActionEvent e) {
        if (e.getActionCommand().equals("Add Cloth")) {
            String brandInput = brand.getText();
            String typeInput = type.getText();
            String colourInput = colour.getText();
            Boolean genderInput = Boolean.valueOf(gender.getText());
            Boolean sizeInput = Boolean.valueOf(size.getText());
            Cloth newCloth = new Cloth(brandInput,typeInput,colourInput,genderInput,sizeInput);
            w1.addCloth(newCloth);
        }
    }

    // perform remove command
    public void removeClothCommand(ActionEvent e) {
        if (e.getActionCommand().equals("Remove Cloth")) {
            String colour = JOptionPane.showInputDialog(null,
                    "Cloth Colour?",
                    "Enter cloth colour",
                    JOptionPane.QUESTION_MESSAGE);

            String type = JOptionPane.showInputDialog(null,
                    "Cloth Type?",
                    "Enter cloth type",
                    JOptionPane.QUESTION_MESSAGE);

            w1.removeCloth(colour,type);


        }
    }

    // perform view command
    public void viewCommand(ActionEvent e) {
        if (e.getActionCommand().equals("View Clothes")) {
            List<Cloth> clothes = w1.getCloth();
            String output = "";
            for (Cloth i : clothes) {
                output = output + i.getBrand() + " " + i.getType() + " " + i.getColour() + "    ";
            }
            JOptionPane.showMessageDialog(null,output,
                    "View CLothes",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // perform view capacity command
    public void viewCapacityCommand(ActionEvent e) {
        if (e.getActionCommand().equals("View Capacity")) {
            int capacity = w1.remainCapacity();
            JOptionPane.showMessageDialog(null,Integer.toString(capacity),
                    "Remaining Capacity",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // perform save command
    public void saveCommand(ActionEvent e) {
        if (e.getActionCommand().equals("Save")) {
            try {
                jsonWriter.open();
                jsonWriter.write(w1);
                jsonWriter.close();
                JOptionPane.showMessageDialog(null,"Successfully Saved to " + JSON_STORE,
                        "Save",JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException fileNotFoundException) {
                JOptionPane.showMessageDialog(null,"Failed to Save",
                        "Save",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // perform load command
    public void loadCommand(ActionEvent e) {
        if (e.getActionCommand().equals("Load")) {
            try {
                w1 = jsonReader.read();
                JOptionPane.showMessageDialog(null,"Successfully Load from " + JSON_STORE,
                        "Load",JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null,"Failed to Load from " + JSON_STORE,
                        "Load",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new WardrobeUI();
    }

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("hi");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        for (Event event: EventLog.getInstance()) {
            System.out.println(event.getDescription());
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void windowGainedFocus(WindowEvent e) {

    }

    @Override
    public void windowLostFocus(WindowEvent e) {

    }

    @Override
    public void windowStateChanged(WindowEvent e) {

    }
}
