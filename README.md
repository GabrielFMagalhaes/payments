# Payments Application

For building and running the application you need:

- [Gradle](https://gradle.org/)
- [Docker](https://www.docker.com/)

## API's

### Local
[![Run in Postman - Localhost](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/c1408c0b4f66dcaa8f99)

### Heroku
[![Run in Postman - Heroku](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/66ea4cf3000b63cc714b)


### Swagger
Visit Swagger URL when running the app
- http://localhost:8080/payments/v1/swagger-ui.html

or in Heroku    
- https://app-payments-challenge.herokuapp.com/payments/v1/swagger-ui.html


## Running

```
docker-compose up --build
```


#### Running with docker

```shell
./gradlew build && docker-compose up --build
```


##  Testing

```shell
./gradlew test
```

> Included with Jacoco Reports


## Deployment

GitHub Actions to automate deploy into Heroku


## Built With

- [Java](https://docs.oracle.com/en/java/) - Programming language
- [Spring Boot](https://spring.io/) - Framework
- [Gradle](https://gradle.org/) - Dependency Management
- [Docker](https://www.docker.com/) - Containerization Platform


## References
Some references about hexagonal architecture:
- https://jivimberg.io/blog/2020/02/01/hexagonal-architecture-on-spring-boot/
- https://medium.com/javarevisited/hands-on-hexagonal-architecture-with-spring-boot-ca61f88bed8b
- https://www.baeldung.com/hexagonal-architecture-ddd-spring
- https://br.sensedia.com/post/use-of-the-hexagonal-architecture-pattern