Java Advanced: Backend Services;
==
Module 1 Task
--

```
mvn spring-boot:run
```
```
java -jar .\target\springfoundation-1.0-jar-with-dependencies.jar
```

# endpoints:

- requires login: admin/password:
  - http://localhost:8080/actuator
  - ```
    curl -X GET -u admin:password http://localhost:8080/actuator
    ```

- custom actuator endpoint, no login required
  - http://localhost:8080/actuator/custom
  - ``` 
    curl -X GET http://localhost:8080/api/dsi
    ```

- datasource info, no login required
  - http://localhost:8080/api/dsi 
  - ``` 
    curl -X GET http://localhost:8080/api/dsi 
    ```

- Populate database:
  - ```
    curl -X POST -u user:password http://localhost:8080/api/populate
    ```

- Read DB records (requires login: user/password):
  - http://localhost:8080/api/all
  - ```
    curl -X GET -u user:password http://localhost:8080/api/all 
    ```




What should be done:
## Spring Auto Configuration

Create Basic Spring application which will have Data Source Configuration provided by spring Auto-Configuration:

- Introduce a configuration(using @Configuration) which should have a method annotated with @Bean which returns a configured data source instance.
- Use https://mvnrepository.com/artifact/com.h2database/h2 as a Data Source.
- Add test which tests your application by saving an entity to the data source.

  ___Solution:___ ***com.learn.epam.springfoundation.configuration.DataSourceConfigDev***

## Custom Configuration

Create Custom configuration for data source based on conditional properties:

- Introduce a configuration(using @Configuration) which should return a data source if an instance of provided interface doesn't exist(@ConditionalOnMissingBean).
- Add test which tests your application by saving an entity to the data source.

 ___Solution:___ 
 - ***com.learn.epam.springfoundation.configuration.DataSourceConfigDev***
 - ***com.learn.epam.springfoundation.AppRepoTests***

## Spring Actuator

- Enable actuator by adding https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-actuator dependency.
- Expose Default Spring Actuator by configuring SecurityWebFilterChain bean.
- Add a new custom actuator endpoint(using @Component and @Endpoint(id = ...)) and return a custom response.

 ___Solution:___ ***com.learn.epam.springfoundation.actuator.CustomEndpoint***

## Spring Profiles (optional, should be done when previous items are complete)

 Separate Data Source Configurations Using Profiles:

- There should be 2 different Data Sources - one for QA and one for DEV.
- Introduce 2 data sources(using @Component and @Profile("QA")/ @Component and @Profile("DEV"))
- Implement test(using @ActiveProfile("DEV")) which tests your application by requesting data from DEV Data Source.

﻿