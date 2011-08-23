package ee.devclub.model;

import javax.persistence.*;

@Entity @Access(AccessType.FIELD)
public class PhotoSpot {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id Long id;

    String name;
    String description;
    Location location;

    PhotoSpot() {
    }

    public PhotoSpot(String name, String description, Location location) {
        this.name = name;
        this.description = description;
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
