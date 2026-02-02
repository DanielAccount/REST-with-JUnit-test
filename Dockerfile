# Stage 1: Build stage
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app

# Copy only the pom.xml first to leverage Docker layer caching for dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests


FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=build /app/target/*.demo-0.0.1-SNAPSHOT.jar


EXPOSE 9091

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]