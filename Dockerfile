# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="feres.foudhaili@esprit.tn"

# Add a volume pointing to /tmp

# Make port 8090 available to the world outside this container
EXPOSE 8080


ADD target/reservation-service-0.0.1-SNAPSHOT.jar app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]