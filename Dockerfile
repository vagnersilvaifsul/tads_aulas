FROM amazoncorretto:17-alpine
WORKDIR /app
COPY ./target/tads_aulas-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
