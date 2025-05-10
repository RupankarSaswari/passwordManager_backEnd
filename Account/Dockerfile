# Stage 1: Build the JAR using Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder


# Set the working directory inside the container
WORKDIR /app

# Copy all project files to the container
COPY . .

# Build the project and skip tests to speed up the build
RUN mvn clean package -DskipTests

# Stage 2: Run the built JAR with a lightweight JDK
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory for the runtime container
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/Account-0.0.1-SNAPSHOT.jar Account-0.0.1-SNAPSHOT.jar

# Run the application
ENTRYPOINT ["java", "-jar", "Account-0.0.1-SNAPSHOT.jar"]

