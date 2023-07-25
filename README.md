# Mama Khichuri Restaurant App - `no-db` Branch

Welcome to the `no-db` branch of the Mama Khichuri restaurant application! This branch demonstrates a simple implementation of the application using only in-memory data structures for data storage. There's no interaction with any kind of database in this branch.

## Prerequisites

You should have Java 8 or later and Maven installed on your system.

## Configuring JDBC datasource to NOT use any database

Spring Boot's auto-configuration, by default, expects a database to connect to. In this case, you need to tell Spring Boot that you're not going to use any database. You can do this by adding the following property to your `application.properties` file:

```properties
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
```

This property will exclude `DataSourceAutoConfiguration` class which is responsible for the auto-configuration of the DataSource. By excluding this auto-configuration, Spring Boot won't attempt to configure a database for you, and your application should start without any issues.

Remember that this is only applicable if you're not planning on using any kind of database (like in the `no-db` branch). If you're going to use a database, you'll need to remove this property and provide the necessary configurations.

## Building and Running the Application

1. Clone this repository to your local machine.

2. Navigate to the project directory (where the `pom.xml` file resides) in the terminal or command line.

3. Switch to the `no-db` branch using the command:

   ```bash
   git checkout no-db
   ```

4. Build the application with Maven:

   ```bash
   mvn clean install
   ```

5. Run the application:

   ```bash
   mvn spring-boot:run
   ```

The application should now be running and listening for HTTP requests on port 8080 on your localhost.

## API Endpoints

In this branch, we expose the following RESTful endpoints:

- `GET /foodItems`: Returns a list of all food items in the menu.
- `GET /foodItems/{id}`: Returns the food item with the specified UUID. If no such item exists, it returns an error message.
- `POST /foodItems`: Creates a new food item. This endpoint expects a JSON body with `name` and `type`.
- `PUT /foodItems/{id}`: Updates the specified food item. This endpoint expects a JSON body with `name` and `type`.
- `DELETE /foodItems/{id}`: Deletes the specified food item.

For example, to create a new food item, you would send a `POST` request to `localhost:8080/foodItems` with a JSON body like:

```json
{
  "name": "Bhuna Khichuri",
  "type": "Rice Dish"
}
```

You can also do so using cURL. 

Add a food item:

```json
curl -X POST -H "Content-Type: application/json" -d '{"name": "Bhuna Khichuri", "type": "Appetizer"}' http://localhost:8080/foodItems
```

This will add a new food item with the name "Bhuna Khichuri" and type "Main Dish".

Update an existing food item:

```json
curl -X PUT -H "Content-Type: application/json" -d '{"name": "Updated Khichuri", "type": "Main Dish"}' http://localhost:8080/foodItems/{id}
```

Note: Use the GET `localhost:8080/foodItems` endpoint to find the {id} for 'Bhuna Khichuri'. Replace {id} with the ID of the food item you want to update. This will update the name and type of that food item.

## Exercise for Reader

There's a known bug in this branch. When you update an existing food item using the `PUT` endpoint, the application creates a duplicate entry instead of updating the existing one.

Can you identify why this is happening and how to fix it? Check the `FoodItemRepository` class, specifically the `save` method. The culprit line is commented out for you to spot!

Once you've tried fixing the issue, you can uncomment the line in the `save` method to see the solution.

## Further details on the bug

This `save` method is part of `FoodItemRepository`, and is responsible for both creating a new `FoodItem` and updating an existing one. The input is a `FoodItem` object, and the method returns the same object after saving it.

Here is a detailed explanation of the code:

- `foodItems.add(foodItem);` This line adds the input `FoodItem` to the `foodItems` list. This is the "saving" part of the method.
- `return foodItem;` Finally, the method returns the saved `FoodItem`.

Now, you may notice a comment and a commented line of code:

```java
// Without the following line, an update will actually duplicate entry
// foodItems.removeIf(item -> item.getId().equals(foodItem.getId()));
```

This comment is pointing out a potential problem with the `save` method as it's currently written. Right now, the `save` method does not distinguish between a new `FoodItem` and an existing `FoodItem` that's being updated. Therefore, if you were to save a `FoodItem` with the same ID as an existing `FoodItem` (in other words, if you were to update a `FoodItem`), the method would add the updated `FoodItem` to the list without removing the old one. This would result in two `FoodItem` objects with the same ID in the `foodItems` list, which is not desirable.

The commented line of code is a solution to this problem. It uses the `removeIf` method to remove any `FoodItem` from the list that has the same ID as the input `FoodItem` before adding the input `FoodItem` to the list. This ensures that if an existing `FoodItem` is updated, the old `FoodItem` will be removed from the list, preventing duplicate entries.

Here's an exercise for you, the reader: Can you find a scenario where this bug might become apparent? Try creating and updating `FoodItem` objects, and see what happens.

Once you've done that, uncomment the line:

```java
foodItems.removeIf(item -> item.getId().equals(foodItem.getId()));
```

Now, try the same operations again. You should see that the bug is fixed. Remember to always test your code thoroughly to ensure it behaves as expected!


## Contributing

Issues/PRs are welcome.