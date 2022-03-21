# Microserviços, Spring Boot 2.6.4 & Spring Cloud 2021.0.1 & JMIX 1.2.0

Implementation of Microservices using Spring Boot 2.6.4 and Spring Cloud 2021.0.1
This project was entirely based on Piotr's project (https://github.com/piomin/sample-spring-microservices-new) and updated to use the latest libraries.

### Usage

In the most cases you need to have Maven and JDK17+. The best way to run the sample applications is with IDEs like IntelliJ IDEA or Eclipse.

## Architecture

This sample microservices-based system consists of the following modules:

- **admin-service** - a module that uses Spring Boot Admin for monitoring Spring Boot applications and using the Spring Boot Actuator module in the background.
- **config-service** - a module that uses Spring Cloud Config Server for running configuration server in the `native` mode. The configuration files are placed on the classpath.
- **discovery-service** - a module that depending on the example it uses Spring Cloud Netflix Eureka as an embedded discovery server.
- **gateway-service** - a module that Spring Cloud Gateway for running Spring Boot application that acts as a proxy/gateway in our architecture.
- **employee-service** - a module containing the first of our sample microservices that allows to perform CRUD operation on in-memory repository of employees
- **department-service** - a module containing the second of our sample microservices that allows to perform CRUD operation on in-memory repository of departments. It communicates with employee-service.
- **organization-service** - a module containing the third of our sample microservices that allows to perform CRUD operation on in-memory repository of organizations. It communicates with both employee-service and department-service.

The following picture illustrates the architecture described above.

![Architecture](https://github.com/ikuchmin/jmix-microservices/blob/master/assets/img/architecture.png)
