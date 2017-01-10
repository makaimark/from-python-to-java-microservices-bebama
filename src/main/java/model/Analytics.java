package model;

import java.sql.Timestamp;
import java.util.Currency;

public class Analytics {

    private Integer id;
    private Integer webshopId;
    private String sessionId;
    private Timestamp startTime;
    private Timestamp endTime;
    private LocationModel Location;
    private Float amount;
    private Currency currency;

    public Analytics(Integer webshopID, String sessionId, Timestamp startTime, Timestamp endTime, LocationModel location, Float amount, String currency) {
        this.webshopId = webshopID;
        this.sessionId = sessionId;
        this.webshopId = webshopID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.Location = location;
        this.amount = amount;
        this.currency = Currency.getInstance(currency);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWebshopId() {
        return webshopId;
    }

    public void setWebshopId(Integer webshopId) {
        this.webshopId = webshopId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public LocationModel getLocation() {
        return Location;
    }

    public void setLocation(LocationModel location) {
        Location = location;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

//    public Timestamp spentTime() {
//        return this.endTime - this.startTime;
//    }
}
