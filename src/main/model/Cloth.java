package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a cloth having a brand, a type, for men or for women, for kids or adults.
public class Cloth implements Writable {
    private String brand;
    private String type;
    private String colour;
    private Boolean gender;
    private Boolean size;

    /*
    Requires: brand has a non-zero length and type has a non-zero length.
    Effects: brand of the cloth is set to brandname; type of the cloth
    is set to typename; if gender is true, then the cloth is for men, otherwise
    it is for women; if size is true, the cloth is for adults, otherwise it is
    for kids.
     */

    public Cloth(String brandName, String typeName, String colourNanme, Boolean menOrWomen, Boolean kidsOrAdults) {
        brand = brandName;
        type = typeName;
        colour = colourNanme;
        gender = menOrWomen;
        size = kidsOrAdults;

    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    public String getColour() {
        return colour;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("brand",brand);
        json.put("type",type);
        json.put("colour",colour);
        json.put("gender",gender);
        json.put("size",size);
        return json;
    }


}
