package com.ageipel.crescendo.demo.api;

import com.ageipel.crescendo.demo.svc.impl.YelpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ageipel.crescendo.demo.model.Review;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewsApiImpl implements ReviewsApi {

    @Autowired
    private YelpServiceImpl yelpService;

    @Override
    public ResponseEntity<List<Review>> getReviews(String name) {

        List<Review> reviewList = yelpService.getReviews(name);

        if (reviewList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(reviewList, HttpStatus.OK);
        }

    }

}
