package ui;

import model.Cloth;
import model.Wardrobe;
import org.json.JSONTokener;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class WardrobeApp {
    private Wardrobe w1;
    private Scanner input1;
    private Scanner input2;
    private Scanner input3;
    private Scanner input4;
    private Scanner input5;
    private Scanner inputLoad;
    private Scanner inputSave;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/wardrobe.json";





    public WardrobeApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runWardrobe();
    }

    private void runWardrobe() {
        boolean goingOn = true;
        String command3 = null;
        String commandSave = null;

        input3 = new Scanner(System.in);
        inputSave = new Scanner(System.in);


        // Initialization
        intialize();

        while (goingOn) {
            showMenu();
            command3 = input3.next();
            command3.toLowerCase();
            if (command3.equals("quit")) {
                goingOn = false;
            } else {
                excuteCommand(command3);
            }
        }
        System.out.println("Do you want to save the wardrobe? Please enter yes or no.");
        commandSave = inputSave.next();
        if (commandSave.equals("yes")) {
            saveWardrobe();
        }
        System.out.println("Good bye");

    }

    private void showMenu() {
        System.out.println("\nPlease Select following");
        System.out.println("\tadd -> add a new cloth to the wardrobe");
        System.out.println("\tremove -> remove a cloth from the wardrobe");
        System.out.println("\tview -> view all the clothes in the wardrobe");
        System.out.println("\tspace -> display the remaining space in the wardrobe");
        System.out.println("\tsave -> save wardrobe to file");
        System.out.println("\tload -> load wardrobe from file");
        System.out.println("\tquit -> quit");
    }

    private void intialize() {
        String commandLoad = null;
        String command1 = null;
        String command2 = null;
        inputLoad = new Scanner(System.in);
        input1 = new Scanner(System.in);
        input2 = new Scanner(System.in);
        System.out.println("Do you want to load the previous saved wardrobe? Please enter yes or no.");
        commandLoad = inputLoad.next();
        if (commandLoad.equals("yes")) {
            loadWardrobe();
        } else {
            System.out.println("Please enter the name and capacity of the wardrobe seperately");
            System.out.println("Name");
            command1 = input1.next();
            System.out.println("Capacity");
            command2 = input2.next();
            w1 = new Wardrobe(command1,Integer.valueOf(command2));
        }

    }

    private void excuteCommand(String command) {
        if (command.equals("add")) {
            commandAdd();
        } else if (command.equals("remove")) {
            commandRemove();

        } else if (command.equals("view")) {
            System.out.println(w1.viewClothes());

        } else if (command.equals("space")) {
            System.out.println(w1.remainCapacity());

        } else if (command.equals("save")) {
            saveWardrobe();
        } else if (command.equals("load")) {
            loadWardrobe();
        } else {
            System.out.println("Invalid input");
        }

    }

    // Modifies: this
    // Effect: perform the add command
    private void commandAdd() {
        String brandInput = null;
        String typeInput = null;
        String colourInput = null;
        String genderInput = null;
        String sizeInput = null;
        input4 = new Scanner(System.in);
        System.out.println("Please enter the brand of the cloth");
        brandInput = input4.next();
        System.out.println("Please enter the type Of the cloth");
        typeInput = input4.next();
        System.out.println("Please enter the colour of the cloth");
        colourInput = input4.next();
        System.out.println("If the cloth is for Men, enter true, else enter false");
        genderInput = input4.next();
        System.out.println("If the cloth is for Adults, enter true, else enter false");
        sizeInput = input4.next();
        Cloth newCloth = new Cloth(brandInput,typeInput,
                colourInput,(genderInput.equals("true")),(sizeInput.equals("true")));
        if (w1.addCloth(newCloth)) {
            System.out.println("Cloth successfully added");
        } else {
            System.out.println("The wardrobe is full");
        }


    }

    private void commandRemove() {
        input5 = new Scanner(System.in);
        String colourInput = null;
        String typeInput = null;
        System.out.println("Please enter the colour of the cloth");
        colourInput = input5.next();
        System.out.println("Please enter the type of the cloth");
        typeInput = input5.next();
        if (w1.removeCloth(colourInput,typeInput)) {
            System.out.println("Cloth successfully removed");
        } else {
            System.out.println("Cloth do not exist");
        }


    }

    public void saveWardrobe() {
        try {
            jsonWriter.open();
            jsonWriter.write(w1);
            jsonWriter.close();
            System.out.println("Save " + w1.getName() + "to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to file" + JSON_STORE);
        }
    }

    public void loadWardrobe() {
        try {
            w1 = jsonReader.read();
            System.out.println("Loaded " + w1.getName() + "from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to load from file " + JSON_STORE);
        }
    }

}
