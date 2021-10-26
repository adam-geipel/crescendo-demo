package com.ageipel.crescendo.demo.svc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import tech.redroma.yelp.YelpBusiness;

import java.util.List;

/***
 * Container class to encapsulate Business list to allow for marshall/unmarshall of Yelp Businesses list
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessList {

    private List<YelpBusiness> businesses;

    public List<YelpBusiness> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<YelpBusiness> businesses) {
        this.businesses = businesses;
    }
}
