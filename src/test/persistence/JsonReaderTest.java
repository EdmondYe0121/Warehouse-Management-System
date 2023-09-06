package persistence;

import model.Cloth;
import model.Wardrobe;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Wardrobe w1 = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // none
        }
    }

    @Test
    void testReaderEmptyWardrobe() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWardrobe.json");
        try {
            Wardrobe w1 = reader.read();
            assertEquals("first",w1.getName());
            assertEquals(20,w1.getCapacity());
        } catch (IOException e) {
            fail("Unable to read from file");
        }
    }

    @Test
    void testReaderGeneralWardrobe() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWardrobe.json");
        try {
            Wardrobe w1 = reader.read();
            assertEquals("TestWardrobe",w1.getName());
            assertEquals(30,w1.getCapacity());
            assertEquals(2,w1.getCurrentOccupence());
            List<Cloth> clothes = w1.getCloth();
            checkCloth("Nike","T-shirt","White",clothes.get(0));
            checkCloth("Puma","Jeans","Black",clothes.get(1));
        }
        catch (IOException e) {
            fail("Unable to read from file");
        }
    }
}
