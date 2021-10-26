# Yelp API for Crescendo Collective

## By Adam Geipel

### Overview

This is a basic Yelp API client, submitted as a skills assessment for Crescendo Collective. 
 
### Setup
This Spring Boot API requires basic initial setup to define the authorization properties for the Yelp API. 

This file is located in: `crescendo-boot/src/main/resources/application.yaml`

The sensitive values for the API Key and Client Id are ommitted from this project in the repository, and will need to be re-added for local testing.


### Acknowledgements

I used a pre-packaged library for the Yelp API by [RedRoma](https://github.com/RedRoma/YelpJavaClient) to handle the API calls. This code however appears to require maintenance, as Yelp API no longer uses Oauth2 to authenticate with Yelp's API. As such, I kept the dependency in to save me some time in writing the modeling for the API outputs and opted to use Spring's WebClient library to write the API calls. If there was not a time constraint, I would likely not use it all and write my own class library, as it seems that all of the author's class properties are public (I would opt to make them private with my own setters and getters to manipulate them).

Using the [OpenAPI codegen library](https://github.com/OpenAPITools/openapi-generator) was a design decision I made that, in this context, seems like overkill, however I wanted to demonstrate my typical preferred approach of designing APIs with a contract-first methodology.