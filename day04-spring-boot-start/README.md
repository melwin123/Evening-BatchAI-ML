# Day 4 - Spring Boot project

## Create the project
Use https://start.spring.io with: Maven, Java 17, Spring Boot 3.2.x,
dependencies **Spring Web**. Or copy the `pom.xml` in this folder.

## Run
```bash
mvn spring-boot:run
curl http://localhost:8080/api/health
```

## What to show on screen
1. The Maven dependency tree: `mvn dependency:tree` - one starter pulls in
   Tomcat, Jackson, Spring MVC, logging.
2. Change `server.port` to 9090 in `application.properties` and restart.
3. `target/bfsi-app-1.0.0.jar` after `mvn clean package`, then
   `java -jar target/bfsi-app-1.0.0.jar` - the server is inside the jar.

## Sprint 3 deliverable
A running Spring Boot application with a health endpoint and externalised
configuration.
