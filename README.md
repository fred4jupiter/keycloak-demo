# keycloak-demo
Spring Boot Keycloak demo application using Keycloak 4.0.0 with Spring Boot 2.0.3.

## Setup Keycloak

You have to configure two clients in Keycloak. Both clients are configured the same. You can also import the ream-export
located in `/src/keycloak/realm-export.json`.

- create realm: `showcase`
- create client `keycloak-demo1` and `keycloak-demo2`
- Access Type: confidential
- Standard Flow enabled
- Redirect URL: `http://localhost:8081/*` bzw. `http://localhost:8082/*` 
- create credential for each one and configure it in `application.properties` and `application-app2.properties`
- define a role `ROLE_PRODUCTS`
- create a user with this role assigned

## Setup applications

Customize the settings in `application.properties` and `application-app2.properties` for your needs:

- realm
- resource (clientId)
- auth-server-url
- secret

## How to run the showcase?

This showcase demonstrates Keycloak within Spring Boot. You can start the same application in two different
profiles so that it behaves like two different applications.

After configuring keyloak you have to do:

Start keycloak.

Start application 1: 
```bash
mvn spring-boot:run
```
You can open up application 1 at [http://localhost:8081](http://localhost:8081)

Start application 2:
```bash
mvn spring-boot:run -Drun.profiles=app2
```
You can open up application 2 at [http://localhost:8082](http://localhost:8081)

