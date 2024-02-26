FROM openjdk:17-jdk-alpine
MAINTAINER voyages_team
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

