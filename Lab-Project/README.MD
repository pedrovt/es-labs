# Software Engineering

## Lab Project
To run, open a command prompt on the root of the project and follow the steps:

### A. Package

Run `.\mvnw.cmd -DskipTests package`

`mvn -DskipTests package` if Maven is installed

### B. Deploy application

Run `docker-compose up`

### C. Test application
- To see flights: `localhost:8080/flights`
    - E.g. `localhost:8080/flights?lamin=24`
    - Can be a bit slow opening
    - Query parameters lomin, lamin, lomax, lamax, are optional
- To add a flight: `localhost:8080/addFlight`
    - With equivalent arguments
- To see logging or data messages from topics `logs` and `data`, respectively
    - `localhost:8080/logging`