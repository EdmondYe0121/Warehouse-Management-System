package persistence;

import model.Cloth;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonTest {
    protected void checkCloth(String brand, String type, String colour, Cloth cloth) {
        assertEquals(brand,cloth.getBrand());
        assertEquals(type,cloth.getType());
        assertEquals(colour,cloth.getColour());

    }
}
