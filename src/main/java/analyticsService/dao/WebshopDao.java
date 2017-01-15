package analyticsService.dao;

import analyticsService.model.Webshop;

public interface WebshopDao {
    void add(Webshop webshop) throws Exception;
    Webshop findByApyKey(String apiKey);
}
