FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/ecommerce-backend-0.0.1-SNAPSHOT.jar app.jar

# Dynamically set the profile and port from environment
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=cloud", "-Dserver.port=${PORT}", "-jar", "app.jar"]
