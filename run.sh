#!/bin/bash

# Show usage
usage() {
    echo "Usage: $0 <framework> [version] [port] [mavenLocal]"
    echo ""
    echo "Arguments:"
    echo "  framework    Required. Either 'grails' or 'spring'"
    echo "  version      Optional. Version to use (default: grails=7.0.2, spring=3.5.7)"
    echo "               Examples: 7.0.2, 7.0.2-SNAPSHOT, 3.5.7, 4.0.0-RC1, 4.0.0-SNAPSHOT"
    echo "  port         Optional. Port number (808X format)"
    echo "  mavenLocal   Optional. Use mavenLocal repository"
    echo ""
    echo "Default ports:"
    echo "  Grails 7.0.2:           8081"
    echo "  Grails 7.0.2-SNAPSHOT:  8082"
    echo "  Spring Boot 3.5.x:      8083"
    echo "  Spring Boot 4.x:        8084"
    echo ""
    echo "Examples:"
    echo "  $0 grails"
    echo "  $0 spring"
    echo "  $0 grails 7.0.2-SNAPSHOT"
    echo "  $0 spring 4.0.0-RC1"
    echo "  $0 spring 4.0.0-RC1 8085"
    echo "  $0 grails mavenLocal"
    echo "  $0 spring 4.0.0-SNAPSHOT mavenLocal 8090"
    exit 1
}

# Check if no arguments provided
if [ $# -eq 0 ]; then
    usage
fi

# Parse framework (required first argument)
FRAMEWORK="$1"
if [ "$FRAMEWORK" != "grails" ] && [ "$FRAMEWORK" != "spring" ]; then
    echo "Error: First argument must be 'grails' or 'spring'"
    echo ""
    usage
fi
shift

# Default values
GRAILS_VERSION="7.0.2"
SPRING_BOOT_VERSION="3.5.7"
CUSTOM_PORT=""
USE_MAVEN_LOCAL=""
VERSION=""

# Parse remaining arguments
for arg in "$@"; do
    if [[ "$arg" =~ ^[0-9]+$ ]] && [ "$arg" -ge 1024 ] && [ "$arg" -le 65535 ]; then
        CUSTOM_PORT="$arg"
    elif [ "$arg" == "mavenLocal" ]; then
        USE_MAVEN_LOCAL="-PuseMavenLocal=true"
    elif [[ "$arg" =~ ^[0-9]+\.[0-9]+\.[0-9]+ ]] || [[ "$arg" =~ -SNAPSHOT$ ]] || [[ "$arg" =~ -RC[0-9]+$ ]]; then
        VERSION="$arg"
    fi
done

# Set version and default port based on framework
if [ "$FRAMEWORK" == "grails" ]; then
    if [ -n "$VERSION" ]; then
        GRAILS_VERSION="$VERSION"
    fi

    # Determine default port for Grails
    if [[ "$GRAILS_VERSION" =~ -SNAPSHOT$ ]]; then
        PORT="8082"
    else
        PORT="8081"
    fi
else
    # Spring
    if [ -n "$VERSION" ]; then
        SPRING_BOOT_VERSION="$VERSION"
    fi

    # Determine default port for Spring Boot
    if [[ "$SPRING_BOOT_VERSION" =~ ^4\. ]]; then
        PORT="8084"
    else
        PORT="8083"
    fi
fi

# Override with custom port if provided
if [ -n "$CUSTOM_PORT" ]; then
    PORT="$CUSTOM_PORT"
fi

# Run the appropriate demo
if [ "$FRAMEWORK" == "spring" ]; then
    echo "Running Spring Boot $SPRING_BOOT_VERSION on port $PORT with build directory build-$SPRING_BOOT_VERSION"
    cd spring-demo
    CMD="./gradlew bootRun $USE_MAVEN_LOCAL --project-prop buildDir=build-$SPRING_BOOT_VERSION -PspringBootVersion=$SPRING_BOOT_VERSION --args=\"--server.port=$PORT\""
    echo "$CMD"
    eval $CMD
else
    echo "Running Grails $GRAILS_VERSION on port $PORT with build directory build-$GRAILS_VERSION"
    cd grails-demo
    CMD="./gradlew bootRun $USE_MAVEN_LOCAL --project-prop buildDir=build-$GRAILS_VERSION -PgrailsVersion=$GRAILS_VERSION --args=\"--server.port=$PORT\""
    echo "$CMD"
    eval $CMD
fi
