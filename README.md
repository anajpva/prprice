# Product Price Service

This project consists of an application developed in Spring Boot that provides a REST endpoint to request product prices from a chain of stores within a specified date range.

## Problem description

The company's e-commerce database includes a table called `PRICES` which reflects the final price (PVP) and the rate applied to a product from a chain within certain dates. The relevant fields of the table are as follows:

- **BRAND_ID**: Identifier of the store chain.
- **START_DATE**, **END_DATE**: Date range in which the specified price rate applies.
- **PRICE_LIST**: Identifier of the applicable price rate.
- **PRODUCT_ID**: Product identifier.
- **PRIORITY**: Price application disambiguator. If two rates coincide within a date range, the one with the highest priority (greater numeric value) is applied.
- **PRICE**: Final sale price.
- **CURR**: ISO currency code.

## Project requirements

The project must meet the following requirements:

- Build an application/service in Spring Boot.
- Provide a REST query endpoint that accepts the following input parameters: application date, product identifier, and chain identifier.
- Return the following output data: product identifier, chain identifier, applicable rate, application dates, and final price to apply.
- Use an in-memory H2 database and initialize it with example data.

## Architecture and technologies used

The application follows the hexagonal architecture and uses the following technologies:

- Spring Boot for the development of the REST service.
- H2 in-memory database.
- OpenAPI for API documentation.
- Docker for containerization of the application.

## Instructions for running the application

To run the application, follow these steps:

1. Build the Docker image:

   ``` 
   ./mvnw dockerfile:build
    ``` 
   
2. Start the application:

   ```  
   docker run -it -p 8080:8080 pprice:latest
   ``` 

## API Documentation

For detailed API documentation, please refer to the [OpenAPI specification](./code/pprice-infrastructure-components/pprice-components-rest/src/main/resources/openapi-rest.yaml).


### Note

It is important to note that the date *range returned* refers to the date range within which the price will not change. That same price can be available at some other point in the timeline.
