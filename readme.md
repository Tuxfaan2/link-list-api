# link-list-api:

### Development configuration:

application-development.yaml:

``` 
server:
  port: 
spring:
  liquibase:
    change-log: db/master.xml
  datasource:
    url: 
    username: 
    password: 
    driver-class-name: org.postgresql.Driver
link-list-api:
  api-user: develop
  password: develop
  meilisearch-url: 
  meilisearch-api-key: 
```