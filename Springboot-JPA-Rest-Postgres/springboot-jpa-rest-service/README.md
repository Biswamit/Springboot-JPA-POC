# SpringBoot JPA Rest Server

# Tooling Information

1. `Development`
- Java
- SpringBoot
- Maven
- Postgresql DB

# Pre-requisite Installation and Deployment Information.
## Install all pre-requisite
## Install Java
```
TODO
```
## Install Maven
```
TODO
```
## Install Postgresql
```
TODO
```
## Install Dbeaver
```
TODO
```
## SB JPA Rest example showing JPA relationship for the use cases
O2O-P2C-Unidirectional-With-Shared-Primary-Key
![O2O-P2C-Unidirectional-With-Shared-Primary-Key](/Springboot-JPA-Rest-Postgres/springboot-jpa-rest-service/src/main/resources/static//O2O-C2P-Uni-Spk----Employee-AddressTable.png)

O2O-P2C-Bidirectional-With-Shared-Primary-Key
![O2O-C2P-Unidirectional-With-Shared-Primary-Key](/Springboot-JPA-Rest-Postgres/springboot-jpa-rest-service/src/main/resources/static//O2O-C2P-Uni-Spk----Employee-AddressTable.png)

O2O-P2C-Unidirectional-With-Shared-Primary-Key
![O2O-P2C-Biirectional-With-Shared-Primary-Key](/Springboot-JPA-Rest-Postgres/springboot-jpa-rest-service/src/main/resources/static//O2O-P2C-Bi-Spk----Employee-AddressTable.png)

O2O-P2C-Bidirectional-With-Parent-Foreign-Key
![O2O-P2C-Bidirectional-With-Parent-Foreign-Key](/Springboot-JPA-Rest-Postgres/springboot-jpa-rest-service/src/main/resources/static//O2O-P2C-Bi-Pfk----Employee-AddressTable.png)

### Checkout code Springboot-JPA-POC and go to the folder
```
cd Springboot-JPA-POC
```
## Dev Server in Localhost

### Pre-req Timescale with DB setup in Localhost
```
TODO
```
### Run springboot-jpa-rest-service with Local TimescaleDB in Localhost
```
cd Springboot-JPA-POC/springboot-jpa-rest-service
Run `mvn clean compile package -DskipTests=true` for a the Springboot JPA Rest Server.
Run `mvn spring-boot:run` for the Springboot JPA Rest Server. Navigate to `http://localhost:9081/`
```
* Spring JPA Rest Service Actuator. Navigate to http://localhost:9081/actuator
* For Spring JPA Rest Service Swagger. Navigate to http://localhost:9081/swagger-ui/index.html#/
* Use Postman to open Spring-boot-JPA-POC-Rest-API.postman_collection.json and hit the API to get the data

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.13-SNAPSHOT/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.13-SNAPSHOT/maven-plugin/reference/html/#build-image)

### Guides
The following guides illustrate how to use some features concretely:

## Further help on this project
- biswamit.sarkar@gmail.com (Dev)
