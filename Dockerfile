FROM openjdk:21-jdk-alpine

WORKDIR /app

COPY target/MSCards-0.0.1-SNAPSHOT.jar MSCards.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "MSCards.jar"]