package persistence;

// code refer from JsonSerializationDemo
// Represents a writer that writes JSON representation of a wardrobe to file
import model.Wardrobe;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {
    private String destination;
    private static final int TAB = 4;
    private PrintWriter writer;

    // Effects: Construct writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // Modifies: this
    // Effects: open writer throws FileNotFoundException if destination file cannot
    // be found for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // Modifies: this
    // Effects: writes JSON representation of wardrobe to file
    public void write(Wardrobe wardrobe) {
        JSONObject json = wardrobe.toJson();
        saveToFile(json.toString(TAB));
    }

    // Modifies: this
    // Effects: closes writer
    public void close() {
        writer.close();
    }

    // Modifies: this
    // Effects: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
