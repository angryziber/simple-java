package ee.devclub.rest;

import ee.devclub.model.Location;
import ee.devclub.model.PhotoSpot;
import ee.devclub.model.PhotoSpotRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class PhotoSpotResourceTest {
    PhotoSpotResource resource = new PhotoSpotResource();

    @Before
    public void initMocks() throws Exception {
        resource.repo = mock(PhotoSpotRepository.class);
    }

    @Test
    public void newPhotoSpotsArePersisted() throws Exception {
        resource.newPhotoSpot("Aegna island", "WWI defence structures", 59.583771f, 24.749720f);

        ArgumentCaptor<PhotoSpot> captor = ArgumentCaptor.forClass(PhotoSpot.class);
        verify(resource.repo).persist(captor.capture());

        PhotoSpot spot = captor.getValue();
        assertThat(spot.getName(), is("Aegna island"));
        assertThat(spot.getDescription(), is("WWI defence structures"));
        assertThat(spot.getLocation(), is(new Location(59.583771f, 24.749720f)));
    }
}
