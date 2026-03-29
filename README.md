🛍️ E-Commerce Product Microservice
A lightweight Spring Boot REST API for managing e-commerce product catalogs.

📖 Overview
In a distributed e-commerce platform, separating domain logic is critical for scalability and maintainability. This project is a dedicated Product Microservice responsible solely for the product catalog.

It solves the problem of tightly coupled monolithic architectures by providing a standalone service that other internal services (like an Order or Inventory service) can query to verify product details, check stock quantities, and retrieve pricing.

✨ Features
RESTful API: Exposes endpoints to add new products and fetch existing product details.

DTO Pattern: Utilizes Request and Response Data Transfer Objects (ProductRequest, ProductResponse) to separate internal entities from API contracts.

Custom Exception Handling: Implements ProductServiceCustomException for graceful error handling when products are not found.

In-Memory Database Integration: Ready-to-use H2 database configuration for rapid development and testing.
🛠 Tech StackComponentTechnologyDescriptionLanguageJava 17Core programming language.FrameworkSpring Boot 3.3.0Application framework and IoC container.PersistenceSpring Data JPAORM layer for database interactions.DatabaseH2 DatabaseIn-memory relational database for rapid dev/testing.UtilitiesLombokAnnotation processor for reducing boilerplate code.Build ToolMavenDependency management and build automation

flowchart TD
    Client([Client / API Gateway]) -->|HTTP REST| Controller[ProductController]
    
    subgraph Product Service
        Controller -->|DTOs| Service[ProductService / Impl]
        Service -->|JPA Entities| Repository[ProductRepository]
    end
    
    Repository -->|SQL| Database[(H2 Database)]
    
    %% Error Flow
    Service -.->|Throws| ExceptionHandler[Custom Exception Handler]
    
    subgraph Product Service
        Controller -->|DTOs| Service[ProductService / Impl]
        Service -->|JPA Entities| Repository[ProductRepository]
    end
    
    Repository -->|SQL| Database[(H2 Database)]
    
    %% Error Flow
    Service -.->|Throws| ExceptionHandler[Custom Exception Handler]
📁 Project Structure
├── src/main/java/com/dailycodebuffer/ProductService/
│   ├── ProductServiceApplication.java   # Main Spring Boot Entry Point
│   ├── controller/
│   │   └── ProductController.java       # REST API Endpoints
│   ├── entity/
│   │   └── Product.java                 # JPA Entity (Table Mapping)
│   ├── service/
│   │   ├── ProductService.java          # Service Interface
│   │   └── ProductServiceImpl.java      # Business Logic Implementation
│   └── repository/
│       └── ProductRepository.java       # Spring Data JPA Interface
├── pom.xml                              # Maven Dependencies
└── HELP.md                              # Spring Boot auto-generated help
(Note: DTOs like ProductRequest, ProductResponse, and custom exceptions are utilized within the code and typically reside in model/ or exception/ packages).
🚀 Installation
1. Clone the repository

Bash
git clone https://github.com/vaibhavidhenge23/e-commerce-micro-services-.git
cd e-commerce-micro-services-
2. Build the project
Make sure you have JDK 17+ installed. Use the provided Maven wrapper to build the project.

Bash
./mvnw clean install
💻 Usage
1. Start the application

Bash
./mvnw spring-boot:run
The service will start by default on http://localhost:8080 (unless configured otherwise in your application.properties).

2. Access the H2 Database Console
If H2 console is enabled in your properties, navigate to:
http://localhost:8080/h2-console

⚙️ Configuration
Environment variables and application properties are typically managed in src/main/resources/application.properties or application.yml. Standard configurations include:

Properties
# Example application.properties configuration
server.port=8080

# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:productdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
🔌 API Modules
Add a Product
URL: /product

Method: POST

Body:

JSON
{
  "productName": "Wireless Headphones",
  "price": 299,
  "quantity": 50
}
Response: Returns the generated productId (Long).
🤝 Contributing
Contributions are welcome! If you'd like to improve this microservice (e.g., adding update/delete endpoints, integrating a real database, or adding security), please follow these steps:

Fork the Project

Create your Feature Branch (git checkout -b feature/AmazingFeature)

Commit your Changes (git commit -m 'Add some AmazingFeature')

Push to the Branch (git push origin feature/AmazingFeature)

Open a Pull Request

🗺 Roadmap
[ ] Implement PUT /product/{id} for updating product inventory.

[ ] Implement DELETE /product/{id} to remove a product.

[ ] Migrate from H2 in-memory DB to PostgreSQL or MySQL for production readiness.

[ ] Add Spring Cloud Gateway integration.

[ ] Integrate Eureka Discovery Client for service registration.

[ ] Implement centralized exception handling using @ControllerAdvice.

📄 License
This project is open-source and available under the MIT License. (Suggest adding a LICENSE file to the repository if one does not exist).
