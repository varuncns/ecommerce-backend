# Use Java 21 base image from Eclipse Temurin
FROM eclipse-temurin:21-jdk

# Set working directory inside the container
WORKDIR /app

# Copy the Spring Boot jar file into the container
COPY target/ecommerce-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (used by Cloud Run)
EXPOSE 8080

# Set entrypoint to use the correct Spring profile and dynamic PORT
ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=cloud -Dserver.port=$PORT -jar app.jar"]
