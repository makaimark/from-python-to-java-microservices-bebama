package analyticsService.dao;

import analyticsService.model.Analytics;

import java.util.List;
import java.sql.*;

public interface AnalyticsDao {
    void add(Analytics model);
    List<Analytics> findByWebshop(String apiKey);
    List<Analytics> findByWebshopTime(String apiKey, Timestamp start, Timestamp end);
    List<Analytics> findSessionId(String sessionId);
}
