package ee.devclub.model;

import org.hibernate.dialect.H2Dialect;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import javax.sql.DataSource;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class HibernatePhotoSpotRepositoryIntegrationTest {
    DataSource dataSource;
    HibernatePhotoSpotRepository repo = new HibernatePhotoSpotRepository();

    @Before
    public void setUp() throws Exception {
        dataSource = new DriverManagerDataSource("jdbc:h2:mem:hibernate;DB_CLOSE_DELAY=-1", "sa", "sa");

        System.setProperty("hibernate.dialect", H2Dialect.class.getName());
        System.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        AnnotationSessionFactoryBean sessionFactory = new AnnotationSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setAnnotatedClasses(new Class[] {PhotoSpot.class});
        sessionFactory.afterPropertiesSet();

        repo.hibernate = new HibernateTemplate(sessionFactory.getObject());
    }

    @Test
    public void loading() throws Exception {
        dataSource.getConnection().createStatement()
            .execute("insert into PhotoSpot (id, name, description, latitude, longitude) values (1, 'Kohtuotsa', 'Mega place!', 59.437755, 24.74209)");

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
        repo.hibernate.clear();

        PhotoSpot spot2 = repo.getAllSpots().get(0);
        assertThat(spot2, not(sameInstance(spot)));
        assertThat(spot2, equalTo(spot));
    }
}
