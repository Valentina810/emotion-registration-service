FROM amazoncorretto:11-alpine-jdk
WORKDIR /app
COPY . /app
RUN ./mvnw clean package
ENTRYPOINT ["java","-jar","emotion-registration-service.jar"]