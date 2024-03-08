FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app
COPY supplier/pom.xml .
RUN mvn dependency:resolve
COPY supplier/src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar ./supplier.jar
CMD ["java", "-jar", "supplier.jar"]