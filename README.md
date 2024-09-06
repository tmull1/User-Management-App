User Management Application This is a Spring Boot-based User Management application with role-based access control (ADMIN/USER).

Setup and Run Instructions Prerequisites Java 17 Maven Postman (for API testing) IntelliJ IDEA (or any IDE) Steps Clone the repository:

bash Copy code git clone https://github.com/tmull1/User-Management-App Open in IntelliJ IDEA: Open the project folder in IntelliJ.

Build the project: Run the following Maven command to download dependencies:

bash Copy code mvn clean install Run the Application: Run the UserManagementAppApplication.java file to start the application.

Access H2 Database Console: Go to http://localhost:8080/h2-console and use the following credentials:

JDBC URL: jdbc:h2:mem:testdb Username: sa Password: (leave blank) API Endpoints Method Endpoint Access Description POST /api/users/register Public Register a new user GET /api/users/all ADMIN only Fetch all users GET /api/users/profile USER/Admin Fetch the current user's profile Postman Usage For protected routes, use Basic Auth with your username and password. Default roles: ADMIN and USER.