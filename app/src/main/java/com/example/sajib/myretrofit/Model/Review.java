package com.example.sajib.myretrofit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("rev_identifier_id")
    @Expose
    private Integer revIdentifierId;
    @SerializedName("rev_identifier_type")
    @Expose
    private String revIdentifierType;
    @SerializedName("rev_id")
    @Expose
    private Integer revId;
    @SerializedName("rev_user_id")
    @Expose
    private Integer revUserId;
    @SerializedName("rev_review")
    @Expose
    private String revReview;
    @SerializedName("rev_rating")
    @Expose
    private Double revRating;
    @SerializedName("rev_date_time")
    @Expose
    private String revDateTime;

    public Integer getRevIdentifierId() {
        return revIdentifierId;
    }

    public void setRevIdentifierId(Integer revIdentifierId) {
        this.revIdentifierId = revIdentifierId;
    }

    public String getRevIdentifierType() {
        return revIdentifierType;
    }

    public void setRevIdentifierType(String revIdentifierType) {
        this.revIdentifierType = revIdentifierType;
    }

    public Integer getRevId() {
        return revId;
    }

    public void setRevId(Integer revId) {
        this.revId = revId;
    }

    public Integer getRevUserId() {
        return revUserId;
    }

    public void setRevUserId(Integer revUserId) {
        this.revUserId = revUserId;
    }

    public String getRevReview() {
        return revReview;
    }

    public void setRevReview(String revReview) {
        this.revReview = revReview;
    }

    public Double getRevRating() {
        return revRating;
    }

    public void setRevRating(Double revRating) {
        this.revRating = revRating;
    }

    public String getRevDateTime() {
        return revDateTime;
    }

    public void setRevDateTime(String revDateTime) {
        this.revDateTime = revDateTime;
    }

}