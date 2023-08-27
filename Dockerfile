FROM amazoncorretto:11-alpine-jdk
WORKDIR /app
COPY .mvn/wrapper .mvn/wrapper
COPY pom.xml .
COPY . .
RUN ./mvnw clean install
ENTRYPOINT ["java","-jar","emotion-registration-service.jar"]
