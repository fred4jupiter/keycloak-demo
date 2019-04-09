# keycloak-demo
Spring Boot Keycloak demo application using Keycloak 5.0.0 with Spring Boot 2.1.4.

## Setup Keycloak

You have to configure two clients in Keycloak. Both clients are configured the same. You can also import the ream-export
located in `/src/keycloak/realm-export.json`.

- create realm: `showcase`
- create client `keycloak-demo1` and `keycloak-demo2`
- Access Type: confidential
- Standard Flow enabled
- Redirect URL: `http://localhost:8081/*` bzw. `http://localhost:8082/*` 
- create credential for each one and configure it in `application.properties` and `application-app2.properties`
- define a role `ROLE_PRODUCTS` in each client
- create a user with this role assigned
- NOTE: with Keycloak 5 you also have to select the client scopes so that the roles will
  be mapped (see Clients -> select you client -> Client Scopes -> Assign 'roles')

## Setup applications

Customize the settings in `application.properties` and `application-app2.properties` for your needs:

- realm
- resource (clientId)
- auth-server-url
- secret

## Custom Theme

In the directory `src/keycloak/theme` you can find the `showcase` theme. Copy the `showcase` folder into 
the `KEYCLOAK_HOME/theme` folder.

To use the theme select the showcase theme under `Realm Settings -> Themes -> Login Theme -> showcase`.

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

