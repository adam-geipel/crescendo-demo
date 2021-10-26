package com.ageipel.crescendo.demo.svc.impl;

import com.ageipel.crescendo.demo.boot.YelpConfigurationProperties;
import com.ageipel.crescendo.demo.model.Review;
import com.ageipel.crescendo.demo.svc.YelpService;
import com.ageipel.crescendo.demo.svc.client.BusinessClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.redroma.yelp.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/***
 * Service implementation to fulfill API requests for Yelp service
 */
@Service
public class YelpServiceImpl implements YelpService {

    @Autowired
    private YelpConfigurationProperties yelpConfigurationProperties;

    @Autowired
    private BusinessClient businessClient;

    @Override
    public List<Review> getReviews(String name) {
        List<Review> realReviews = new ArrayList<>();

        YelpBusiness business = businessClient.getBusinesses(name).get(0);
        List<YelpReview> reviews = businessClient.getBusinessReviews(business.id);

        return reviews.stream().map(review -> {
            Review reviewSummary = new Review();
            reviewSummary.setUser(review.user.name);
            reviewSummary.setRating(review.rating);
            reviewSummary.setFeedback(review.text);

            return reviewSummary;
        }).collect(Collectors.toList());

    }
}
