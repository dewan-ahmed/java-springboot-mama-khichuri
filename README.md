# Mama Khichuri Restaurant App

This is a Spring Boot application for a fictional Bangladeshi restaurant named "Mama Khichuri". The application provides a basic set of RESTful API endpoints to Create, Read, Update, and Delete (CRUD) food items in the restaurant's menu. 

## API Endpoints

The application defines the following RESTful endpoints:

- GET /foodItems: Fetch all food items in the restaurant.
- GET /foodItems/{id}: Fetch a specific food item by its ID.
- POST /foodItems: Add a new food item to the restaurant menu.
- PUT /foodItems/{id}: Update the details of an existing food item.
- DELETE /foodItems/{id}: Remove a food item from the restaurant menu.

For POST and PUT operations, the request body must be a JSON object that includes name and type fields for the food item. Here is a sample request body:

```json
{
    "name": "Bhuna Khichuri",
    "type": "Main Course"
}
```

This repository is organized in different branches, each demonstrating a different type of data storage mechanism:

- **`no-db` branch:** This branch shows the basic in-memory data structure implementation. It doesn't involve any database, and uses simple Java collections for data storage.
  
- **`h2` branch:** This branch involves usage of Spring Data JPA for object relational mapping, and uses an in-memory H2 database for persistent data storage.

- **`mysql` branch:** This branch, like `h2`, uses Spring Data JPA for object relational mapping, but uses an external MySQL database for persistent data storage. This is closer to a real-world scenario.

You can switch between these branches to understand the differences in the implementation and to choose the best one according to your needs. Here's how to switch between branches:

1. Clone this repository to your local machine.

2. Navigate to the project directory (where the `pom.xml` file resides) in the terminal or command line.

3. Use the command `git checkout <branch-name>` to switch between branches (replace `<branch-name>` with `no-db`, `h2`, or `mysql`).

For instance, to switch to the `h2` branch, you would use:

```bash
git checkout h2
```

**Prerequisite:** Java 8 or later and Maven installed on your system.

## Building and Running the Application

After switching to your desired branch, follow these steps:

1. Run the command `mvn clean install` to build the application. Maven will download all necessary dependencies and perform tests.

2. Once built, you can run the application using the command `mvn spring-boot:run`.

The application should now be running and listening for HTTP requests on port 8080 on your localhost.

## Contributing

Issues/PRs are welcome.