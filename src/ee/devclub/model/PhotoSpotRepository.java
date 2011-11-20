package ee.devclub.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhotoSpotRepository {
    @Autowired HibernateOperations hibernate;

    public List<PhotoSpot> getAllSpots() {
        return hibernate.loadAll(PhotoSpot.class);
    }

    public PhotoSpot persist(PhotoSpot spot) {
        hibernate.saveOrUpdate(spot);
        return spot;
    }
}
