package models.panels.securitydomain.ports;

/**
 * Created by eunderhi on 10/05/16.
 */
public class Port {

    private int value;
    private String name;

    private Port(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static Port create() {
        return new Port("", 0);
    }

    public Port withValue(int value) {
        return new Port(name, value);
    }

    public Port withName(String name) {
        return new Port(name, value);
    }

    public Port withOffset(int offset) {
        return new Port(name, value + offset);
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name.isEmpty() ?
                String.valueOf(value) :
                "${" + name + ":" + value + "}";
    }

}
