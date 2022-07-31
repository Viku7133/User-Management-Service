# User Management System

## Setup Guidelines
configure server port by adding the following line in application.properties file
server.port=${port_name}

configure Database by providing the following properties in application.properties
spring.datasource.url=${db-host-url}
spring.datasource.username=${db user-name}
spring.datasource.password=${db password}

### Build
To build the application execute the following commands in root directory of project
mvn clean install -DskipTests

### Run
To run the application execute the following commands in root directory of project after building the application
java -jar target/userservice-0.0.1-SNAPSHOT.jar 
