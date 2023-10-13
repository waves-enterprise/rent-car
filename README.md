# fab-car-app

Simple application to demonstrate interaction with a contract from the backend-application.

Note:
- To launch the application, you need to launch a local database using the docker compose (or configure the application for yourself and your parameters in properties):
```yaml
version: "3.9"
services:
  postgres:
    image: postgres:13.3
    environment:
      POSTGRES_DB: "fab_car"
      POSTGRES_USER: "postgres"
      POSTGRES_PASSWORD: "docker"
    ports:
      - "5432:5432"
```
- Each endpoint, in addition to returning a list of cars from the database, accepts a mandatory `X-Tx-Sender` header,
which is used to implement the role model in the contract.  
Swagger page - http://localhost:8081/swagger-ui/index.html#/