package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WardrobeTest {
    private Cloth c1;
    private Cloth c2;
    private Cloth c3;
    private Cloth c4;
    private Wardrobe w1;

    @BeforeEach
    void runBefore() {
        c1 = new Cloth("Nike","T-shirt","White",true,true);
        c2 = new Cloth
                ("Adidas", "Jacket","Black",true,false);
        c3 = new Cloth("Puma","Jeans","White",false,false);
        c4 = new Cloth("Puma", "Jeans", "White", false, false);
        w1 = new Wardrobe("First Floor",3);


    }

    @Test
    void TestConstructor() {
        assertEquals("First Floor",w1.getName());
        assertEquals(3,w1.getCapacity());
        assertEquals(0,w1.getCurrentOccupence());

    }

    @Test
    void TestaddCloth() {
        assertTrue(w1.addCloth(c1));
        assertEquals(1,w1.getCurrentOccupence());
        assertEquals(2,w1.remainCapacity());
        w1.addCloth(c2);
        w1.addCloth(c3);
        assertEquals(3,w1.getCurrentOccupence());
        assertEquals(0,w1.remainCapacity());
        assertFalse(w1.addCloth(c4));
        assertEquals(0,w1.remainCapacity());


    }

    @Test
    void TestremoveCloth() {
        w1.addCloth(c1);
        assertTrue(w1.removeCloth("White","T-shirt"));
        assertEquals(0,w1.getCurrentOccupence());
        w1.addCloth(c1);
        w1.addCloth(c3);
        w1.addCloth(c4);
        w1.removeCloth("White","Jeans");
        assertEquals(2,w1.getCurrentOccupence());
        List<Cloth> testclothes = w1.getCloth();
        assertFalse(testclothes.contains(c3));
        assertTrue(testclothes.contains(c4));
        assertFalse(w1.removeCloth("Black","Dress"));
        assertEquals(2,w1.getCurrentOccupence());
    }

    @Test
    void TestviewClothes() {
        w1.addCloth(c1);
        w1.addCloth(c2);
        List<String> testallclothes = w1.viewClothes();
        assertEquals("Nike T-shirt White",testallclothes.get(0));
        assertEquals("Adidas Jacket Black",testallclothes.get(1));
    }
}