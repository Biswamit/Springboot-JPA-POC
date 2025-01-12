# SpringBoot JPA Rest Server

# Tooling Information

1. `Development`
- Java
- SpringBoot
- Maven
- Postgresql DB

# Pre-requisite Installation and Deployment Information.
## Install all pre-requisite
## Install NVM(this is 1st time and only if NVM is not installed in dev machine)
```
For windows: Download nvm-setup.exe from https://github.com/coreybutler/nvm-windows/releases
```
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
### Checkout code Springboot-JPA-POC and go to the folder
```
cd Springboot-JPA-POC
```
## Dev Server in Localhost
### Run springboot-jpa-rest-service with Local PostgresqlDB in Localhost
```
* Stop the localhost Postgresql Service usng `sudo systemctl stop postgresql`
* cd Springboot-JPA-POC/springboot-jpa-rest-service
* Run `mvn clean compile package -DskipTests=true` for a the Springboot AL Rest Server.
* Run `mvn spring-boot:run` for the Springboot AL Rest Server. Navigate to `http://localhost:9081/`
```
* Springboot JPA Rest Service Actuator. Navigate to http://localhost:9081/actuator
* For Springboot JPA Rest Service Swagger. Navigate to http://localhost:9081/swagger-ui/index.html#/
* Use Postman to open Spring-boot-AL-Rest-API.postman_collection.json and hit the API to get the data

## Dev Server in Docker
```
cd Springboot-JPA-POC/docker
Run `./docker-create.sh -m all for a the Springboot AL Docker.
```

```
Note : docker-create.sh -m stand for mode and it supports the following
* all - For building all docker image and container 
* jpa - For building just springboot-jpa-rest-service docker image and container
* pgex - For building just postgres.exporter.db docker image and container
* db - For building just postgresql.jpa.rest.service.db docker image and container
* gr - For building just grafana-jpa-rest docker image and container
* pr - For building just prometheus-jpa-rest docker image and container
```
* Prometheus ScrapePool. Navigate to http://localhost:9090/targets?search=&scrapePool=
* Prometheus Metrics. Navigate to http://localhost:9090/metrics
* Grafana Dashboard. Navigate to http://localhost:3000/dashboards
* Grafana Dashboard - Datasource
* > 4701 - JVM Micrometer
* > 12900 - SB APM Dashboard
* > 14114 - PG Exporter
* > 6102 - Postgresql table
* >  19004 - SB 3.x Stats
* Springboot JPA Rest Service Actuator. Navigate to http://localhost:9081/actuator
* Springboot JPA Rest Service Swagger. Navigate to http://localhost:9081/swagger-ui/index.html#/
* Use Postman to open Spring-boot-AL-Rest-API.postman_collection.json and hit the API to get the data

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.13-SNAPSHOT/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.13-SNAPSHOT/maven-plugin/reference/html/#build-image)

### Guides
The following guides illustrate how to use some features concretely:

* [Service Registration and Discovery with Eureka and Spring Cloud](https://spring.io/guides/gs/service-registration-and-discovery/)
* [Using Spring Cloud Gateway](https://github.com/spring-cloud-samples/spring-cloud-gateway-sample)

## Further help on this project
- biswamit.sarkar@trellix.com (Dev)
