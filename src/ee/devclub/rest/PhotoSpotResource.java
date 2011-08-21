package ee.devclub.rest;

import ee.devclub.model.Location;
import ee.devclub.model.PhotoSpot;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

import static java.util.Arrays.asList;

@Path("photo-spots")
@Produces("application/json")
public class PhotoSpotResource {
    @GET
    public List<PhotoSpot> getAllSpots() {
        return asList(new PhotoSpot("Kohtuotsa vaateplatvorm", new Location(59.437755f, 24.74209f)));
    }
}
