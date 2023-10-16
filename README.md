# fab-car-app

Simple application to demonstrate interaction with a contract from the backend-application.

Note:
- To launch the application, you need to launch a local database using the `docker-compose.yml` 
or configure the application for yourself and your parameters in spring properties `spring.datasource` 
in [application.yml](fab-car-webapp-app/src/main/resources/application.yml):
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/fab_car
    username: your_username
    password: your_password
```

- Each endpoint, in addition to returning a list of cars from the database, accepts a mandatory `X-Tx-Sender` header,
which is used to implement the role model in the contract.  
Swagger page - http://localhost:8081/swagger-ui/index.html#/