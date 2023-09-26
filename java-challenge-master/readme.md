### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.



### Instructions

- download the zip file of this project
- create a repository in your own github named 'java-challenge'
- clone your repository in a folder on your machine
- extract the zip file in this folder
- commit and push

- Enhance the code in any ways you can see, you are free! Some possibilities:
  - Add tests
  - Change syntax
  - Improve doc and comments
  - Fix any bug you might find
- Edit readme.md and add any comments. It can be about what you did, what you would have done if you had more time, etc.
- Send us the link of your repository.

#### Restrictions
- use java 8


#### What we will look for
- Readability of your code
- Documentation
- Comments in your code 
- Appropriate usage of spring boot
- Appropriate usage of packages
- Is the application running as expected
- No performance issues

#### Your experience in Java

Hello All, I have been primarily a backend developer for the last 8 years, working with Java and since the last 
few years I have also worked on Spring Boot applications for Application Development and Enhancement. I have
experience working in Agile methodology and along with developing backend REST APIs I also develop JUNIT test cases
and resolve SonarQube code issues to ensure optimum code quality.

For this application, I performed following operations:
- Enhanced the Spring Boot version to the latest version to utilise the latest annotations and JUNIT support.
- Enhanced from old Swagger to OpenAPI specification compatible with latest Spring Boot
- Enhanced REST APIs by providing proper Response Entities, changed syntax and removed Sysout code.
- Introduced Custom and Global Exception Handling scenarios in code
- Introduced a custom REST API to find all employees in a particular department and wrote custom JPA query. 
- Introduced OPENAPI specifications and code comments to make code more understandable to users.
- Added proper logs as applicable
- Removed redundant annotations
- Introduced DTO class to ensure communication from frontend to backend is happening via DTO and not Entity for security
- Added proper code comments
- Maintained proper package structure for classes as well as test classes
- Added necessary configuration changes to application.properties file
- Introduced few examples to showcase Java 8 stream feature, lombok annotations.

Further application can be enhanced to introduce more custom APIS with business logic, introducing Spring Security,
converting application.properties file to yml file for better readability. Database can be added rather than using H2
database.
