FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/brogrammers-special-forum-api-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} bsf-api.jar
ENTRYPOINT ["java","-jar","/bsf-api.jar"]