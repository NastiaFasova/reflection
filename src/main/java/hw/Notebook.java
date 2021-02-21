package hw;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Notebook extends Computer implements Cleanable {
    private String OC;
    private double diagonal;
    private int batteryCapacity;

    @CustomNotation
    public Notebook makePhoto() {
        System.out.println("The photo was done");
        return this;
    }

    @CustomNotation
    public int charge(int percents) {
        return batteryCapacity;
    }
}
