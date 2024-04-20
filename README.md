# Online Store API

This project is a Java Spring Boot application designed to serve as the backend for an online store. It provides various routes and functionalities to manage products, orders, users, and authentication.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Setup](#setup)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Authentication](#authentication)
- [Entities](#entities)
- [License](#license)

## Features

- **Product Management**: CRUD operations for managing products.
- **Order Management**: Creating, updating, and retrieving orders.
- **User Management**: Handling user registration, authentication, and profile management.
- **Security**: Utilizes Spring Security for authentication and authorization.
- **Data Persistence**: Uses JPA and Hibernate for database interaction.

## Technologies

- Java 20
- Spring Boot 3.2.4
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL/PostgreSQL (choose one)
- Maven

## Setup

1. **Clone the repository**:
   ```
   git clone <repository-url>
   ```

2. **Navigate to the project directory**:
   ```
   cd online-store-api
   ```

3. **Configure the database**:
   - Create a MySQL or PostgreSQL database.
   - Update the `application.properties` file with your database configurations.

4. **Build the project**:
   ```
   mvn clean install
   ```

5. **Run the application**:
   ```
   mvn spring-boot:run
   ```

## Usage

Once the application is running, you can interact with it through HTTP requests to the provided endpoints.

## Endpoints

- **Product Endpoints**:
  - `GET /api/products`: Retrieve all products.
  - `GET /api/products/{id}`: Retrieve a specific product.
  - `POST /api/products`: Create a new product.
  - `PUT /api/products/{id}`: Update an existing product.
  - `DELETE /api/products/{id}`: Delete a product.

- **Order Endpoints**:
  - `GET /api/orders`: Retrieve all orders.
  - `GET /api/orders/{id}`: Retrieve a specific order.
  - `POST /api/orders`: Create a new order.
  - `PUT /api/orders/{id}`: Update an existing order.
  - `DELETE /api/orders/{id}`: Delete an order.

- **User Endpoints**:
  - `POST /api/register`: Register a new user.
  - `POST /api/login`: Login with username and password.
  - `GET /api/profile`: Retrieve user profile information.
  - `PUT /api/profile`: Update user profile information.

## Authentication

This application uses JSON Web Token (JWT) for authentication. After successful login, the server responds with a JWT token which should be included in the header of subsequent requests for accessing protected endpoints.

## Entities

- **Address**: Represents a user's address information.
- **Cart**: Represents a user's shopping cart.
- **CartItem**: Represents an item in the user's shopping cart.
- **Category**: Represents a category for grouping products.
- **Order**: Represents an order placed by a user.
- **OrderItem**: Represents an item within an order.
- **Payment**: Represents a payment made for an order.
- **Product**: Represents a product available in the store.
- **Role**: Represents a user role for authorization purposes.
- **User**: Represents a user of the online store.

## License

This project is licensed under the [MIT License](LICENSE).

---

