FROM openjdk:17-jdk-alpine
LABEL authors="Gavin"
COPY target/Receipt-Reward-Backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]