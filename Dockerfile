### Project Tracker App
FROM maven:3.9.9-eclipse-temurin-24-alpine AS builder

# Get all dependencies ready
WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN RUN mvn clean package -DskipTests || cat /app/target/surefire-reports/*.txt

# Get application running
FROM eclipse-temurin:24-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

CMD ["/bin/sh"]

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]