package model;

import event.Event;
import event.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


// Represents a wardrobe with name and capacity
public class Wardrobe implements Writable {
    private String name;
    private int capacity;
    private ArrayList<Cloth> clothes;
    private int currentOccupence;


    /*
    Requires: place has a non-zero length and capacity is greater than 0
    Effects: name is set to place; capacity is set to numberOfClothes; currentoccupence
    is set to 0 ArraryList<Cloth> contains the existing clothes in a wardrobe, and it
    is initially set to empty.
     */

    public Wardrobe(String place, int numberOfClothesAvailable) {
        name = place;
        capacity = numberOfClothesAvailable;
        currentOccupence = 0;
        clothes = new ArrayList<>();
        EventLog.getInstance().logEvent(new Event("A new wardrobe is created"));

    }

    // Requires: a cloth
    // Modifies: this
    // Effects: a new cloth is added to the wardrobe if the capacity is not full,
    // return true. If the capacity is already full, do not add and return false.

    public Boolean addCloth(Cloth c) {
        if (currentOccupence < capacity) {
            currentOccupence++;
            clothes.add(c);
            EventLog.getInstance().logEvent(new Event("A cloth is added to the wardrobe"));
            return true;
        } else {
            return false;
        }
    }

    // Requires: the cloth must be in the wardrobe, at least one cloth should exist in the wardrobe
    // Modifies: this
    // Effects: remove the cloth if the cloth is in the wardrobe;
    // otherwise no change.
    public Boolean removeCloth(String clothColor, String clothType) {
        boolean success = true;
        for (Cloth i : clothes) {
            if (Objects.equals(i.getColour(), clothColor) && Objects.equals(i.getType(), clothType)) {
                clothes.remove(i);
                currentOccupence--;
                EventLog.getInstance().logEvent(new Event("A cloth is removed from the wardrobe"));
                break;
            } else {
                success = false;
            }


        }
        return success;
    }

    // Effects: return the remaining space in the wardrobe
    public int remainCapacity() {
        EventLog.getInstance().logEvent(new Event("The remaining capacity is being viewed by user"));
        return capacity - currentOccupence;
    }

    // Effects: return all the clothes in the wardrobe(including their
    // brand, type and colour.
    public List<String> viewClothes() {
        List<String> allclothes = new ArrayList<>();
        for (Cloth i : clothes) {
            allclothes.add(i.getBrand() + " " + i.getType() + " " + i.getColour());
        }
        return allclothes;
    }


    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentOccupence() {
        return currentOccupence;
    }

    public List<Cloth> getCloth() {
        EventLog.getInstance().logEvent(new Event("All clothes in the wardrobe are viewed by the user"));
        return clothes;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("capacity",capacity);
        json.put("currentOccupence",currentOccupence);
        json.put("clothes",clothesToJson());
        return json;
    }

    private JSONArray clothesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Cloth c: clothes) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }
}
