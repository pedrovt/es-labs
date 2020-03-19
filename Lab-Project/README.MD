# Software Engineering

## Lab Project
To run, open a command prompt on the root of the project and follow the steps:

### A. Package

Run `.\mvnw.cmd -DskipTests package`

`mvn -DskipTests package` if Maven is installed

### B. Deploy application

Run `docker-compose up`

### C. Test application
- Can be a bit slow before first being able to use the application
- To see flights: `localhost:8080/labproject-1.0.0/flights`
    - E.g. `localhost:8080/labproject-1.0.0/flights?lamin=24`
    - Query parameters lomin, lamin, lomax, lamax, are optional
- To add a flight: `localhost:8080/labproject-1.0.0/addFlight`
    - With equivalent arguments
- To see logging or data messages from topics `logs` and `data`, respectively
    - `localhost:8080/labproject-1.0.0/logging`
- To publish a message:
    - `localhost:8080/labproject-1.0.0/kafka/publish?message=hello`
    - This will automatically publish the message hello to the topic data and a logging info on topic logs