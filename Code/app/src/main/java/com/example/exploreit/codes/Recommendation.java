package com.example.exploreit.codes;

import java.util.Date;

/**
 * Created by Cain on 03-02-2016.
 */
public class Recommendation {
    private int userid,recomid;
    private String description,name,type,imageid;
    private Date timestamp;

    public boolean isApiGenerated() {
        return apiGenerated;
    }

    public void setApiGenerated(boolean apiGenerated) {
        this.apiGenerated = apiGenerated;
    }

    private boolean apiGenerated;

    public double getLati() {
        return lati;
    }

    public void setLati(double lati) {
        this.lati = lati;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    private double lati, longi;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getRecomid() {
        return recomid;
    }

    public void setRecomid(int recomid) {
        this.recomid = recomid;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    private String rating;
}
