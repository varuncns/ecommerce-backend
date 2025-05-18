# Use a Maven image to build the app
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use a lightweight JDK to run the app
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/ecommerce-backend-0.0.1-SNAPSHOT.jar app.jar

# Optional, useful for local debugging — not required by Cloud Run
EXPOSE 8080

# Spring Boot picks up Cloud Run port via env var
ENV PORT=8080
ENTRYPOINT ["java", "-jar", "app.jar"]
