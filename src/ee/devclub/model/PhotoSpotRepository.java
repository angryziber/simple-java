package ee.devclub.model;

import java.util.List;

public interface PhotoSpotRepository {
    List<PhotoSpot> getAllSpots();

    PhotoSpot persist(PhotoSpot spot);
}
