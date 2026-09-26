# Date Time Demo

Demonstrates how various Date formats render in Grails and Spring Boot

## Usage

```bash
./run.sh <framework> [version] [port] [mavenLocal]
```

**Arguments:**
- `framework` - Required. Either `grails` or `spring`
- `version` - Optional. Version to use (default: grails=8.0.0-RC1, spring=4.1.1)
- `port` - Optional. Custom port (808X format)
- `mavenLocal` - Optional. Use mavenLocal repository

Grails 8 needs JDK 21 or later (`JAVA_HOME`); the Spring demo builds with a JDK 25 toolchain.

**Default Ports:**
- Grails 8.0.0-RC1: 8081
- Grails 8.0.0-SNAPSHOT: 8082
- Spring Boot 3.5.x: 8083
- Spring Boot 4.x: 8084

**Examples:**
```bash
./run.sh grails                    # Grails 8.0.0-RC1 on port 8081
./run.sh grails 8.0.0-SNAPSHOT     # Grails 8.0.0-SNAPSHOT on port 8082
./run.sh spring                    # Spring Boot 4.1.1 on port 8084
./run.sh spring 3.5.7              # Spring Boot 3.5.7 on port 8083
./run.sh spring 4.1.1 8085         # Custom port
./run.sh grails mavenLocal         # Use mavenLocal repository
```

## Endpoints

http://localhost:8081/dateTime
http://localhost:8082/dateTime

http://localhost:8081/dateTime/show.json
http://localhost:8081/dateTime/show.gson

http://localhost:8082/dateTime/show.json
http://localhost:8082/dateTime/show.gson

http://localhost:8083/

http://localhost:8084/
