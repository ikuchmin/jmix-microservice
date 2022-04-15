# Microserviços, Spring Boot 2.6.4 & Spring Cloud 2021.0.1 & JMIX 1.2.0

Implementation of Microservices using Spring Boot 2.6.4, Spring Cloud 2021.0.1 and JMIX 1.2.0
This project was entirely based on Piotr's project (https://github.com/piomin/sample-spring-microservices-new)
and updated to use the latest libraries.

### Usage

In the most cases you need to have Maven and JDK17+. The best way to run the sample
applications is with IDEs like IntelliJ IDEA or Eclipse.

### Run

Use `distribution` task for building jar or runnable jar depending on type project.

Use docker-compose to build and run all services. Do not forget using rebuild 
and recreate docker-compose flags 

#### Configuration

Add `client` to the auth server KeyCloak

## Architecture

This sample microservices-based system consists of the following modules:

- **admin-service** - a module that uses Spring Boot Admin for monitoring Spring Boot applications and using the Spring Boot Actuator module in the background.
- **admin-react-service** - a Admin React module that giving UI to manage resources like employees, departments, organizations.
- **config-service** - a module that uses Spring Cloud Config Server for running configuration server in the `native` mode. The configuration files are placed on the classpath.
- **discovery-service** - a module that depending on the example it uses Spring Cloud Netflix Eureka as an embedded discovery server.
- **gateway-service** - a module that Spring Cloud Gateway for running Spring Boot application that acts as a proxy/gateway in our architecture.
- **employee-service** - a JMIX module containing the first of our sample microservices that allows to perform CRUD operation on in-memory repository of employees
- **department-service** - a JMIX module containing the second of our sample microservices that allows to perform CRUD operation on in-memory repository of departments. It communicates with employee-service.
- **organization-service** - a module containing the third of our sample microservices that allows to perform CRUD operation on in-memory repository of organizations. It communicates with both employee-service and department-service.

### Communication

- Department service -> Employee service using WebClient
- Organization service -> Employee service using Feign client
- Organization service -> Department service using Feign client

The following picture illustrates the architecture described above.

![Architecture](https://github.com/ikuchmin/jmix-microservice/raw/master/assets/img/architecture.png)


### Gradle project structure

To create new gradle module/project just add empty build.gradle in a folder