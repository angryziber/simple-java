package ee.devclub.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernatePhotoSpotRepository implements PhotoSpotRepository {
    @Autowired HibernateOperations hibernate;

    @Override
    public List<PhotoSpot> getAllSpots() {
        return hibernate.loadAll(PhotoSpot.class);
    }

    @Override
    public PhotoSpot persist(PhotoSpot spot) {
        hibernate.saveOrUpdate(spot);
        return spot;
    }
}
