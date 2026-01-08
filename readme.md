# link-list-api:

### Development configuration:

Depending on whether or not you are using the default Docker Compose, you may
need to change some things.

application-development.yaml:

``` yaml
server:
  port: 8081
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: http://localhost:8080/realms/dev-realm
          audiences: link-list-api
  datasource:
    url: jdbc:postgresql://localhost:5432/devdb
    username: devuser
    password: devpassword
    driver-class-name: org.postgresql.Driver
link-list-api:
  meilisearch-url: http://localhost:7700
  meilisearch-api-key: masterKey123
```