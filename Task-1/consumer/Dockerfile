FROM eclipse-temurin:21-alpine as builder


WORKDIR /app

COPY .mvn/ .mvn
COPY ../mvnw pom.xml ./
RUN ../mvnw dependency:resolve

COPY src ./src


RUN ./mvnw clean package -Dmaven.test.skip

FROM eclipse-temurin:21-alpine
EXPOSE 8081
COPY --from=builder /app/target/consumer-0.0.1-SNAPSHOT.jar /consumer.jar
CMD ["java", "-jar", "/consumer.jar"]
