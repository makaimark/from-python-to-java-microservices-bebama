package dao;

import model.Analytics;

import java.util.List;
import java.sql.*;

public interface AnalyticsDao {
    void add(Analytics model);
    List<Analytics> findByWebshop(int webshop);
    List<Analytics> findByWebshopTime(int webshop, Timestamp start, Timestamp end);
    List<Analytics> findSessionId(String sessionId);
}
