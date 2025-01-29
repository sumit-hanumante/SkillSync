# Use a multi-arch image compatible with Apple Silicon
FROM eclipse-temurin:17-jdk-jammy

VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]