FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/KafkaPOC-0.0.1-SNAPSHOT.jar kafkaPOC.jar

ENTRYPOINT ["java", "-jar", "/app/kafkaPOC.jar"]