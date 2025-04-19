# ====== Stage 1: Build with Maven ======
FROM maven:3.9.9-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml first and resolve dependencies
COPY pom.xml .

RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests

# ====== Stage 2: Run with JDK ======
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/movieApi-0.0.1-SNAPSHOT.jar .

# Expose port if your app uses one (optional, e.g., Spring Boot)
EXPOSE 8080

# Start the app
ENTRYPOINT ["java", "-jar", "/app/movieApi-0.0.1-SNAPSHOT.jar"]