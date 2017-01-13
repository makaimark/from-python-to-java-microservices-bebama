package dao;

import model.LocationVisitor;

import java.util.List;
import java.sql.*;

public interface LocationVisitorDao {
    List<LocationVisitor> locationsByWebshop(int webshop);
    List<LocationVisitor> locationsByWebshopTime(int webshop, Timestamp start, Timestamp end);

}
