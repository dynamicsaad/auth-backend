# First stage: Build the JAR
FROM maven:3.8.7-eclipse-temurin-17-alpine AS build

# Set working directory
WORKDIR /app

# Copies all files from the host system’s current directory into the container’s /app directory.
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Second stage: Run the JAR
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Create temp volume
VOLUME /tmp

# Takes myapp.jar from the build stage and copies it into the current /app directory.
COPY --from=build /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Set environment variables
ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql-db:3306/my_db?useSSL=false&allowPublicKeyRetrieval=true
ENV SPRING_DATASOURCE_USERNAME=user
ENV SPRING_DATASOURCE_PASSWORD=pass


ENTRYPOINT ["java", "-jar", "app.jar"]

