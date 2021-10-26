package com.ageipel.crescendo.demo.svc;

import com.ageipel.crescendo.demo.model.Review;
import java.util.List;

public interface YelpService {

    List<Review> getReviews(String name);
}
