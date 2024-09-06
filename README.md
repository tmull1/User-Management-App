User Management Application
This is a Spring Boot-based User Management System that provides user registration, authentication, and role-based access control (Admin/User). The application uses an in-memory H2 database and Spring Security for authentication and authorization.

Features
User registration with role assignment (USER/ADMIN)
Authentication using Spring Security with BCrypt password encoding
Role-based access control (Admins can view all users, Users can view their profile)
In-memory H2 database for easy setup and testing
Requirements
Java 17
Maven 3.6+
Postman (optional, for API testing)
Getting Started
1. Clone the Repository
   bash
   Copy code
   git clone https://github.com/tmull1/User-Management-App.git
   cd User-Management-App
2. Build the Application
   Before running the application, make sure you have Maven installed. You can build the application with the following command:

bash
Copy code
mvn clean install
3. Run the Application
   Once the build is successful, you can run the Spring Boot application using the following command:

bash
Copy code
mvn spring-boot:run
4. Accessing the Application
   The application will be running at http://localhost:8080. You can interact with the API using Postman or any other API testing tool.

5. H2 Database Console
   The H2 in-memory database is enabled for this application. You can access the H2 console via the following URL:

URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: Leave blank
6. API Endpoints
   Here are the available API endpoints you can test:

Register a User
Method: POST
URL: /api/users/register
Body (JSON):
json
Copy code
{
"username": "admin",
"password": "Password1",
"role": "ADMIN"
}
Get All Users (Admin Only)
Method: GET
URL: /api/users/all
Authorization: Basic Auth (admin credentials)
Get User Profile (Authenticated User)
Method: GET
URL: /api/users/profile
Authorization: Basic Auth (user credentials)
7. Testing Authentication
   You can test the authentication using Postman by adding Basic Auth in the Authorization tab. Use the following credentials for the default admin:

Username: admin
Password: Password1
8. Important Configuration
   Here are the key configurations from the application.properties file:

properties
Copy code
# H2 Database settings
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Application name
spring.application.name=User-Management-App
9. Custom Validation
   This application includes a custom password validation that ensures passwords contain at least one uppercase letter, one lowercase letter, and one digit. You can modify the password validation logic in the PasswordValidator class.

Running Tests
You can run the tests using Maven with the following command:

bash
Copy code
mvn test
