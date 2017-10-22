package demo;

/**
 * Created by anandbarhate on 21/10/2017.
 */
public class Record {
    private String regNumber;
    private String make;
    private String colour;

    public Record(String regNumber, String make, String colour) {
        this.regNumber = regNumber;
        this.make = make;
        this.colour = colour;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
