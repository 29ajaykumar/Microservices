# Stage 1: Build the JAR
FROM maven:3.8.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the JAR
FROM eclipse-temurin:17.0.9_9-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/docker-demo-*.jar app2.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app2.jar"]