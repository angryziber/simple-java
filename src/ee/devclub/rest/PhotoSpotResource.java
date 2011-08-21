package ee.devclub.rest;

import ee.devclub.model.Location;
import ee.devclub.model.PhotoSpot;

import javax.ws.rs.*;
import java.util.List;

import static java.util.Arrays.asList;

@Path("photo-spots")
@Produces("application/json")
public class PhotoSpotResource {
    @GET
    public List<PhotoSpot> getAllSpots() {
        return asList(new PhotoSpot("Kohtuotsa vaateplatvorm", "", new Location(59.437755f, 24.74209f)));
    }

    @POST
    public PhotoSpot newPhotoSpot(@FormParam("name") String name, @FormParam("description") String description,
                                  @FormParam("lat") float lat, @FormParam("lon") float lon) {
        return new PhotoSpot(name, description, new Location(lat, lon));
    }
}
