# Software Engineering

## Lab Project
To run, open a command prompt on the root of the project and follow the steps:

### A. Package

Run `.\mvnw.cmd -DskipTests package` (Windows)

Run `./mvnw -DskipTests package` (Linux)

`mvn -DskipTests package` if Maven is installed

### B. Deploy application

If you're running the project in a Linux machine, open the docker-compose.yml file and change the database volume (comment line 66 and uncomment line 68)
Run `docker-compose build` then `docker-compose up`

### C. Test application
- Can be a bit slow before first being able to use the application (and show a 404 error in the meantime)
- To see flights: `localhost:8080/labproject-1.0.0/flights`
    - E.g. `localhost:8080/labproject-1.0.0/flights?lamin=24`
    - Query parameters lomin, lamin, lomax, lamax, are optional
- To add a flight: `localhost:8080/labproject-1.0.0/addFlight`
    - With equivalent arguments
- To publish a message:
    - `curl -X POST -F 'message=hello' http://localhost:8080/labproject-1.0.0/kafka/publish`
    - or use form in `localhost:8080/labproject-1.0.0/logging` (for testing purposes only)
    - This will automatically publish the message hello to the topic data and a logging info on topic logs
- To see logging or data messages from topics `logs` and `data`, respectively
    - `localhost:8080/labproject-1.0.0/logging`
