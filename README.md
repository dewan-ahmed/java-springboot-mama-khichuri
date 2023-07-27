# Mama Khichuri Application (`h2` branch)

## Overview

The `h2` branch version of the Mama Khichuri Application uses Spring Boot, Jakarta Persistence API (JPA), and an in-memory H2 database. It demonstrates how to integrate JPA and a database into a Spring Boot application for data persistence.

## Code Structure

- `MamaKhichuriApplication.java` is the main entry point of the Spring Boot application.
- `FoodItem.java` is the model class representing a FoodItem. This class is annotated with JPA annotations, defining it as a JPA entity.
- `FoodItemRepository.java` is the repository interface that extends `JpaRepository`, which provides methods for common CRUD operations.
- `FoodItemController.java` is the controller class that handles HTTP requests.

## Application Properties

To use H2 database and JPA, we need to specify the following properties in the `src/main/resources/application.properties` file:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Enabling H2 Console
spring.h2.console.enabled=true

# Custom H2 Console URL
spring.h2.console.path=/h2-console
```

These properties configure the H2 database, enable the H2 console, and specify the JPA dialect to be used.

## How to Run

1. Ensure that you have Java and Maven installed on your machine.
2. Clone the repository and switch to the `h2` branch.
3. Navigate to the root directory of the project and run `mvn spring-boot:run`.
4. The application will be running at `http://localhost:8080`.

## API Endpoints

- GET `/foodItems` - returns a list of all food items.
- GET `/foodItems/{id}` - returns a food item by its UUID.
- POST `/foodItems` - creates a new food item. The new food item data should be included in the request body in JSON format.
- DELETE `/foodItems/{id}` - deletes a food item by its UUID.
- PUT `/foodItems/{id}` - updates a food item by its UUID. The updated food item data should be included in the request body in JSON format.

## Accessing H2 Console

Navigate to `http://localhost:8080/h2-console` in your web browser. Enter the JDBC URL (`jdbc:h2:mem:testdb`), username (`sa`), and password (`password`), then click Connect.

## Contributing

Issues/PRs are welcome.
