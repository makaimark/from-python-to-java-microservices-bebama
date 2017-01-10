package model;

import java.util.Currency;

public class Analytics {

    private  Integer id;
    private Integer webshopId;
    private String sessionId;
    private Integer startTime;
    private Integer endTime;
    private String Location;
    private Float amount;
    private Currency currency;

    public Analytics(Integer webshopID, String sessionId, Integer startTime, Integer endTime, String location, Float amount, String currency){
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

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
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

    public Integer spentTime() {
        return this.endTime - this.startTime;
    }
}
