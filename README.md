The Application.java is the main application entry class.

The application is developed using: 
1) JPA - Uses Spring JPA with JPA Provider Hibernate
2) Spring Framework - Spring Boot for Restful WebServices
3) Jetty - Container
4) H2 database

The Restful endpoints are:
http://localhost:8080/api/stations/DART
http://localhost:8080/api/stations/LIVERPOOL
http://localhost:8080/api/stations/DERBY
http://localhost:8080/api/stations/KINGS CROSS
http://localhost:8080/api/stations/searchAll
http://localhost:8080/api/stations/searchAllThatDoesNotExist
http://localhost:8080/api/stations/health


The application can be started by running the java-stations-test-1.0.0.jar.

Jar path = com\traintracker\java-stations-test\1.0.0\java-stations-test-1.0.0.jar
Command: java -jar java-stations-test-1.0.0.jar

Packages :
1) Controller - Contains classes related to Rest Controller
2) Domain - JPA entity
3) Repository - JPA Repository
4) Exception - Exceptions
5) Util - JSON formating utilities

JUnit tests are created using the Spring boot starter test.
1) Controller - Contains all the JUnit tests for the Rest endpoints
2) Util - JUnit tests for JSON formating utilities


