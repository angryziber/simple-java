package ee.devclub.model;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.GenerationType.AUTO;

@Entity @Access(FIELD)
public class PhotoSpot {
    @GeneratedValue(strategy = AUTO)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhotoSpot photoSpot = (PhotoSpot) o;
        return !(description != null ? !description.equals(photoSpot.description) : photoSpot.description != null) && !(id != null ? !id.equals(photoSpot.id) : photoSpot.id != null) && !(location != null ? !location.equals(photoSpot.location) : photoSpot.location != null) && !(name != null ? !name.equals(photoSpot.name) : photoSpot.name != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
