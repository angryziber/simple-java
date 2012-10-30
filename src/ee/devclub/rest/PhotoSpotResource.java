package ee.devclub.rest;

import ee.devclub.model.Location;
import ee.devclub.model.PhotoSpot;
import ee.devclub.model.PhotoSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import java.util.List;

@Path("photo-spots")
@Produces("application/json")
public class PhotoSpotResource extends SpringAwareResource {
    @Autowired PhotoSpotRepository repo;
    int maxSpots = 1000;

    @GET
    public List<PhotoSpot> getAllSpots() {
      List<PhotoSpot> allSpots = repo.getAllSpots();
      return allSpots.subList(0, Math.min(maxSpots, allSpots.size()));
    }

    @POST
    public PhotoSpot newPhotoSpot(@FormParam("name") String name, @FormParam("description") String description,
                                  @FormParam("lat") float lat, @FormParam("lon") float lon) {
        return repo.persist(new PhotoSpot(name, description, new Location(lat, lon)));
    }
}
