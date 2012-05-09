package ee.devclub.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.hibernate3.HibernateOperations;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HibernatePhotoSpotRepositoryTest {
    HibernatePhotoSpotRepository repo = new HibernatePhotoSpotRepository();

    @Before
    public void initMocks() throws Exception {
        repo.hibernate = mock(HibernateOperations.class);
    }

    @Test
    public void allPhotoSpotsComeFromDB() throws Exception {
        when(repo.hibernate.loadAll(PhotoSpot.class)).thenReturn(asList(new PhotoSpot("Kohtuotsa", "", new Location(59.437755f, 24.74209f))));

        List<PhotoSpot> spots = repo.getAllSpots();
        assertThat(spots.size(), is(1));
        assertThat(spots.get(0).name, is("Kohtuotsa"));
    }
}
