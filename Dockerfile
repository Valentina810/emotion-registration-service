FROM amazoncorretto:11-alpine-jdk
WORKDIR /app
COPY . .
RUN ./mvnw clean install
ENTRYPOINT ["java","-jar","emotion-registration-service.jar"]
