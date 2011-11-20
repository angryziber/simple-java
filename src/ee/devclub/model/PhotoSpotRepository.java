package ee.devclub.model;

import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.Arrays.asList;

@Repository
public class PhotoSpotRepository {
    public List<PhotoSpot> getAllSpots() {
        return asList(new PhotoSpot("Kohtuotsa vaateplatvorm", "", new Location(59.437755f, 24.74209f)));
    }

    public PhotoSpot persist(PhotoSpot spot) {
        return spot;
    }
}
