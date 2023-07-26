# Mama Khichuri Spring Boot Application

## `mysql` Branch

Welcome to the `mysql` branch of the Mama Khichuri Application. This branch contains the implementation of our application using MySQL as the database.

### Setup

#### Requirements:

1. JDK 11 or later
2. Maven 3.6.0 or later
3. A hosted or managed MySQL instance. You can choose any MySQL service you want but if you want to use a free managed MySQL on the cloud, you can [sign up for Aiven](https://console.aiven.io/signup?referral_code=v3tvatqoyzbu3yp2oiy9) (no credit card needed).

#### Steps:

1. Clone the repository and switch to the `mysql` branch:

```bash
git clone https://github.com/yourusername/mamakhichuri.git
cd java-springboot-mama-khichuri
git checkout mysql
```

2. Update the `src/main/resources/application.properties` file with your MySQL configuration:

```properties
spring.datasource.url=jdbc:mysql://HOST:PORT/DATABASE
spring.datasource.username=USERNAME
spring.datasource.password=PASSWORD
spring.jpa.hibernate.ddl-auto=update
```

3. Run the application:

```bash
mvn spring-boot:run
```

The application should now be running and connected to your MySQL database.

### API Endpoints

- `GET /foodItems`: Fetch all food items.
- `GET /foodItems/{id}`: Fetch a single food item by its UUID.
- `POST /foodItems`: Create a new food item.
- `DELETE /foodItems/{id}`: Delete a food item by its UUID.
- `PUT /foodItems/{id}`: Update a food item by its UUID.

### Further improvements

- Try to build a `GlobalExceptionHandler` class, which provides global exception handling across all controller classes. If an exception occurs during a database operation (or elsewhere in a controller method), a suitable HTTP response will be returned.
- Try to build a frontend application and make calls to this backend application.
