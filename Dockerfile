FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM amazoncorretto:21

COPY --from=build build/target/Book-Service-0.0.1-SNAPSHOT.jar /app/Book-Service-0.0.1-SNAPSHOT.jar

EXPOSE 8100

CMD ["java", "-jar", "/app/Book-Service-0.0.1-SNAPSHOT.jar"]