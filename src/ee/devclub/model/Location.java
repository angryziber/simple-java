package ee.devclub.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Location implements Serializable {
    float latitude;
    float longitude;

    public Location() {
    }

    public Location(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return latitude + ", " + longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;
        return Float.compare(location.latitude, latitude) == 0 && Float.compare(location.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        int result = (latitude != +0.0f ? Float.floatToIntBits(latitude) : 0);
        return 31 * result + (longitude != +0.0f ? Float.floatToIntBits(longitude) : 0);
    }
}
