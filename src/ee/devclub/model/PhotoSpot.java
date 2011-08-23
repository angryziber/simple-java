package ee.devclub.model;

public class PhotoSpot {
    String name;
    Location location;

    public PhotoSpot(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }
}
