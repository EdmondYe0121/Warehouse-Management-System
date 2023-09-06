package persistence;

// code refer from JsonSerializationDemo
import model.Cloth;
import model.Wardrobe;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

// Represents a Reader that reads a JSON file and covert to a wardrobe
public class JsonReader {
    private String source;

    //Effects: Constructs reader to find the source
    public JsonReader(String source) {
        this.source = source;
    }

    // Effects: reads Wardrobe from file and returns it;
    // throws an IOException if unable to read
    public Wardrobe read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWardrobe(jsonObject);
    }

    // Effects: read source file as string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();

    }

    // Effects: parses Wardrobe from jsonObject and returns it
    private Wardrobe parseWardrobe(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int capacity = jsonObject.getInt("capacity");
        Wardrobe w1 = new Wardrobe(name,capacity);
        addClothes(w1,jsonObject);
        return w1;
    }

    // Modifies: w1;
    // Effects: parses clothes from jsonObject and adds them to wardrobe
    private void addClothes(Wardrobe wardrobe,JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("clothes");
        for (Object json : jsonArray) {
            JSONObject nextCloth = (JSONObject) json;
            addCloth(wardrobe,nextCloth);
        }
    }

    // Modifies: w1
    // Effects: parses cloth from jsonObject and adds them to wardrobe
    private void addCloth(Wardrobe wardrobe,JSONObject jsonObject) {
        String brand = jsonObject.getString("brand");
        String type = jsonObject.getString("type");
        String colour = jsonObject.getString("colour");
        Boolean gender = jsonObject.getBoolean("gender");
        Boolean size = jsonObject.getBoolean("size");
        Cloth cloth = new Cloth(brand,type,colour,gender,size);
        wardrobe.addCloth(cloth);
    }
}









