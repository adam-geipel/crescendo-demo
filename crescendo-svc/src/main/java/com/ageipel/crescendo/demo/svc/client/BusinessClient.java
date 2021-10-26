package com.ageipel.crescendo.demo.svc.client;

import com.ageipel.crescendo.demo.boot.YelpConfigurationProperties;
import com.ageipel.crescendo.demo.svc.models.BusinessList;
import com.ageipel.crescendo.demo.svc.models.ReviewList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import tech.redroma.yelp.YelpBusiness;
import tech.redroma.yelp.YelpReview;

import java.util.*;

/***
 * Client interface used to interact with Yelp API
 */
@Component
public class BusinessClient {

    @Autowired
    private YelpConfigurationProperties yelpConfigurationProperties;

    /***
     * Using Yelp's API, get a list of businesses
     * @param name the general search term for the business
     * @return A list of Yelp business meeting the name input, located in Milwaukee region
     */
    public List<YelpBusiness> getBusinesses(String name) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(yelpConfigurationProperties.getApiKey());
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        WebClient webClient = WebClient.create(yelpConfigurationProperties.getBaseUrl());

        String businesses = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/businesses/search")
                        .queryParam("term", name)
                        .queryParam("location", "Milwaukee")
                        .build())
                .headers(headers -> headers.addAll(httpHeaders))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        BusinessList businessList;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            businessList = objectMapper.readValue(businesses, BusinessList.class);
        } catch (JsonProcessingException e) {
            // Normally I would use a logging framework here. This would need refactoring.
            System.out.println("Unable to parse JSON");
            e.printStackTrace();
            throw new IllegalArgumentException();
        }

        return businessList.getBusinesses();
    }


    /***
     * Using Yelp's API, get a list of reviews for a given business
     * @param businessId the Unique identifier of the business as defined by Yelp API
     * @return A list of Reviews for the specified business
     */
    public List<YelpReview> getBusinessReviews(String businessId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(yelpConfigurationProperties.getApiKey());
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        WebClient webClient = WebClient.create(yelpConfigurationProperties.getBaseUrl());

        String reviews = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("businesses")
                        .pathSegment(businessId)
                        .pathSegment("reviews")
                        .build())
                .headers(headers -> headers.addAll(httpHeaders))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        ReviewList reviewList;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            reviewList = objectMapper.readValue(reviews, ReviewList.class);
        } catch (JsonProcessingException e) {
            // Normally I would use a logging framework here. This would need refactoring.
            System.out.println("Unable to parse JSON");
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
        List<YelpReview> reviewsOut = reviewList.getReviews();
        Collections.sort(reviewsOut, new Comparator<YelpReview>() {
            @Override
            public int compare(YelpReview lhs, YelpReview rhs) {
                return lhs.rating > rhs.rating ? -1 : (lhs.rating < rhs.rating) ? 1 : 0;
            }
        });

        return reviewsOut;
    }

}
