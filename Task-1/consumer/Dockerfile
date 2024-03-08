FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app
COPY consumer/pom.xml .
RUN mvn dependency:resolve
COPY consumer/src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar ./compose.jar
CMD ["java", "-jar", "compose.jar"]