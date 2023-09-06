package persistence;

import model.Cloth;
import model.Wardrobe;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Wardrobe w1 = new Wardrobe("Test",30);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // none
        }

    }

    @Test
    void testWriterEmptyWardrobe() {
        try {
            Wardrobe w1 = new Wardrobe("TestEmpty",20);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWardrobe.json");
            writer.open();
            writer.write(w1);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWardrobe.json");
            w1 = reader.read();
            assertEquals("TestEmpty",w1.getName());
            assertEquals(20,w1.getCapacity());
            assertEquals(0,w1.getCurrentOccupence());
        } catch (IOException e) {
            fail("Exception shouldn't be thrown");
        }
    }

    @Test
    void testWriterGeneralWardrobe() {
        try {
            Wardrobe wardrobe = new Wardrobe("My Wardrobe",30);
            wardrobe.addCloth(new Cloth
                    ("Nike","T-shirt","White",true,true));
            wardrobe.addCloth(new Cloth
                    ("Puma","Jeans","Black",false,false));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWardrobe.json");
            writer.open();
            writer.write(wardrobe);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWardrobe.json");
            wardrobe = reader.read();
            assertEquals("My Wardrobe",wardrobe.getName());
            assertEquals(30,wardrobe.getCapacity());
            assertEquals(2,wardrobe.getCurrentOccupence());
            List<Cloth> clothes = wardrobe.getCloth();
            assertEquals(2,clothes.size());
            checkCloth("Nike","T-shirt","White",clothes.get(0));
            checkCloth("Puma","Jeans","Black",clothes.get(1));

        } catch (IOException e) {
            fail("Exception shouldn't be thrown");
        }
    }



}
