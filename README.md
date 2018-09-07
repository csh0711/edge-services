# Edge Services with Spring Cloud Netflix Zuul and Spring Cloud Gateway

This repository provides a simple example that demonstrates how to build API Gateways aka. Edge Services with Spring Cloud Netflix Zuul and Spring Cloud Gateway. 
As programming language Kotlin is used.

## Running the application locally

### Starting microservices

After cloning the repo you first have to start the rudimentary microservice apps 'users', 'comments' and 'images'.
This can be done in your IDE (e.g. IntelliJ) or with by using the Spring Boot Maven plugin with ```$ mvn spring-boot:run```.

### Using Zuul

Start the 'zuul-edge-service' app. You should now be able to call e.g. http://localhost:8888/users - which will be routed to your 'users' microservice running under http://localhost:8881.
Make sure to provide a cookie called 'customer-Id' in the HTTP request. The value will be mapped to the Authorization header field, which is checked by the microservices.

### Using Spring Cloud Gateway
Start the 'spring-cloud-gateway' app. You should now be able to call e.g. http://localhost:8889/users - which will be routed to your 'users' microservice running under http://localhost:8881.
Make sure to provide a cookie called 'customer-Id' in the HTTP request. The value will be mapped to the Authorization header field, which is checked by the microservices.

For a more detailed documentation of the used frameworks see their project pages 
- https://cloud.spring.io/spring-cloud-netflix/single/spring-cloud-netflix.html#_router_and_filter_zuul 
- https://cloud.spring.io/spring-cloud-gateway/
