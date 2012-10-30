package ee.devclub.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class JDBCPhotoSpotRepositoryIntegrationTest {
    Connection conn;
    JDBCPhotoSpotRepository repo = new JDBCPhotoSpotRepository();

    @Before
    public void initMockDB() throws Exception {
        repo.dataSource = new DriverManagerDataSource("jdbc:h2:mem:test", "sa", "sa");
        conn = repo.dataSource.getConnection();

        conn.createStatement().execute("create table PhotoSpot (id int auto_increment primary key, name varchar, description varchar, latitude float, longitude float)");
    }

    @After
    public void destroyMockDB() throws Exception {
        conn.close();
    }

    @Test
    public void loading() throws Exception {
        conn.createStatement().execute("insert into PhotoSpot values (1, 'Kohtuotsa', 'Mega place!', 59.437755, 24.74209)");

        List<PhotoSpot> spots = repo.getAllSpots();
        assertThat(spots.size(), is(1));

        PhotoSpot spot = spots.get(0);
        assertThat(spot.name, is("Kohtuotsa"));
        assertThat(spot.description, is("Mega place!"));
        assertThat(spot.location, is(new Location(59.437755f, 24.74209f)));
    }

    @Test
    public void fullCycle() throws Exception {
        PhotoSpot spot = new PhotoSpot("Teletorn", "Tallinn TV Tower", new Location(59.47111f, 24.8875f));
        repo.persist(spot);

        PhotoSpot spot2 = repo.getAllSpots().get(0);
        assertThat(spot2, not(sameInstance(spot)));
        assertThat(spot2, equalTo(spot));
    }
}
