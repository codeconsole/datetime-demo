# Date Time Demo

Demonstrates how various Date formats render in Grails and Spring Boot

## Usage

```bash
./run.sh <framework> [version] [port] [mavenLocal]
```

**Arguments:**
- `framework` - Required. Either `grails` or `spring`
- `version` - Optional. Version to use (default: grails=7.0.2, spring=3.5.7)
- `port` - Optional. Custom port (808X format)
- `mavenLocal` - Optional. Use mavenLocal repository

**Default Ports:**
- Grails 7.0.2: 8081
- Grails 7.0.2-SNAPSHOT: 8082
- Spring Boot 3.5.x: 8083
- Spring Boot 4.x: 8084

**Examples:**
```bash
./run.sh grails                    # Grails 7.0.2 on port 8081
./run.sh grails 7.0.2-SNAPSHOT     # Grails 7.0.2-SNAPSHOT on port 8082
./run.sh spring                    # Spring Boot 3.5.7 on port 8083
./run.sh spring 4.0.0-RC1          # Spring Boot 4.0.0-RC1 on port 8084
./run.sh spring 4.0.0-RC1 8085     # Custom port
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
