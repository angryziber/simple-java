package ee.devclub.model;

public class PhotoSpot {
    String name;
    String description;
    Location location;

    public PhotoSpot(String name, String description, Location location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }
}
