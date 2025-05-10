# Use a lightweight OpenJDK 17 image
FROM eclipse-temurin:17-jdk-alpine

# Create app directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/Account-0.0.1-SNAPSHOT.jar Account-0.0.1-SNAPSHOT.jar

# Run the application
ENTRYPOINT ["java", "-jar", "Account-0.0.1-SNAPSHOT.jar"]
