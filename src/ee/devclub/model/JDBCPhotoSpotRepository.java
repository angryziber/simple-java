package ee.devclub.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCPhotoSpotRepository implements PhotoSpotRepository {
    @Autowired DataSource dataSource;

    public List<PhotoSpot> getAllSpots() {
        Connection conn = null;
        try {
            List<PhotoSpot> spots = new ArrayList<PhotoSpot>();
            conn = dataSource.getConnection();
            ResultSet rs = conn.prepareStatement("select * from PhotoSpot").executeQuery();
            while (rs.next()) {
                spots.add(new PhotoSpot(rs.getString("name"), rs.getString("description"),
                          new Location(rs.getFloat("latitude"), rs.getFloat("longitude"))));
            }
            return spots;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeSilently(conn);
        }
    }

    public PhotoSpot persist(PhotoSpot spot) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("insert into PhotoSpot (name, description, latitude, longitude) values (?, ?, ?, ?)");
            stmt.setString(1, spot.name);
            stmt.setString(2, spot.description);
            stmt.setFloat(3, spot.location.latitude);
            stmt.setFloat(4, spot.location.longitude);
            stmt.execute();
            return spot;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeSilently(conn);
        }
    }

    private void closeSilently(Connection conn) {
        try {
            if (conn != null) conn.close();
        }
        catch (SQLException ignore) {
        }
    }
}
