FROM amazoncorretto:21

COPY target/Book-Service-0.0.1-SNAPSHOT.jar /app/Book-Service-0.0.1-SNAPSHOT.jar

EXPOSE 8100

CMD ["java", "-jar", "/app/Book-Service-0.0.1-SNAPSHOT.jar"]