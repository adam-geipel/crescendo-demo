package com.ageipel.crescendo.demo.svc.models;

import tech.redroma.yelp.YelpReview;

import java.util.List;

/***
 * Container class to encapsulate Reviews list to allow for marshall/unmarshall of Yelp Review list
 */
public class ReviewList {

    private List<YelpReview> reviews;

    public List<YelpReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<YelpReview> reviews) {
        this.reviews = reviews;
    }
}
