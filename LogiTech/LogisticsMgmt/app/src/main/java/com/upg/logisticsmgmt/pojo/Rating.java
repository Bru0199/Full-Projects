package com.upg.logisticsmgmt.pojo;

public class Rating {

    private String createdBy;
    private String createdOn;
    private String ratingValue;

    public Rating() {
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(String ratingValue) {
        this.ratingValue = ratingValue;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "createdBy='" + createdBy + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", ratingValue='" + ratingValue + '\'' +
                '}';
    }
}
