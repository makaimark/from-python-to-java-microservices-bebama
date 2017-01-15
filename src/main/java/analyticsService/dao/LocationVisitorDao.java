package analyticsService.dao;

import analyticsService.model.LocationVisitor;

import java.util.List;
import java.sql.*;

public interface LocationVisitorDao {
    List<LocationVisitor> locationsByWebshop(String apiKey);
    List<LocationVisitor> locationsByWebshopTime(String apiKey, Timestamp start, Timestamp end);

}
