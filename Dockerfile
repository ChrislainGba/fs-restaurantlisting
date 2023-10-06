FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/*.jar /app/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar