#!/bin/bash

# Default values
GRAILS_VERSION="7.0.2"
SPRING_BOOT_VERSION=""
DEMO_TYPE="grails"  # grails, grails-snapshot, spring3, or spring4
CUSTOM_PORT=""

# Parse all arguments to detect demo type and custom port
for arg in "$@"; do
    if [[ "$arg" =~ ^[0-9]+$ ]] && [ "$arg" -ge 1024 ] && [ "$arg" -le 65535 ]; then
        CUSTOM_PORT="$arg"
    elif [ "$arg" == "snapshot" ]; then
        DEMO_TYPE="grails-snapshot"
    elif [ "$arg" == "spring3" ]; then
        DEMO_TYPE="spring3"
    elif [ "$arg" == "spring4" ]; then
        DEMO_TYPE="spring4"
    fi
done

# Set version and default port based on demo type
case $DEMO_TYPE in
    grails-snapshot)
        GRAILS_VERSION="7.0.2-SNAPSHOT"
        PORT="8082"
        ;;
    spring3)
        SPRING_BOOT_VERSION="3.5.7"
        PORT="8083"
        ;;
    spring4)
        SPRING_BOOT_VERSION="4.0.0-RC1"
        PORT="8084"
        ;;
    *)
        # Default: Grails 7.0.0
        GRAILS_VERSION="7.0.2"
        PORT="8081"
        ;;
esac

# Override with custom port if provided
if [ -n "$CUSTOM_PORT" ]; then
    PORT="$CUSTOM_PORT"
fi

# Run the appropriate demo
if [ "$DEMO_TYPE" == "spring3" ] || [ "$DEMO_TYPE" == "spring4" ]; then
    echo "Running Spring Boot $SPRING_BOOT_VERSION on port $PORT with build directory build-$SPRING_BOOT_VERSION"
    cd spring-demo
    CMD="./gradlew bootRun --project-prop buildDir=build-$SPRING_BOOT_VERSION -PspringBootVersion=$SPRING_BOOT_VERSION --args=\"--server.port=$PORT\""
    echo "$CMD"
    eval $CMD
else
    echo "Running Grails $GRAILS_VERSION on port $PORT with build directory build-$GRAILS_VERSION"
    cd grails-demo
    CMD="./gradlew bootRun --project-prop buildDir=build-$GRAILS_VERSION -PgrailsVersion=$GRAILS_VERSION --args=\"--server.port=$PORT\""
    echo "$CMD"
    eval $CMD
fi
