FROM amazoncorretto:11-alpine-jdk
COPY ./target/*.jar emotion-registration-service.jar
ENTRYPOINT ["java","-jar","emotion-registration-service.jar"]