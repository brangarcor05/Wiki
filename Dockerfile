# Fase de compilacion: construye el .jar con Maven (Java 21)
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q dependency:go-offline
COPY src src
RUN mvn -q package -DskipTests

# Fase de ejecucion: solo el entorno de ejecucion de Java 21 (sin Maven ni JDK),
# mucho mas liviana que la imagen de compilacion
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/thymeleaf-0.0.1-SNAPSHOT.jar app.jar
ENV SERVER_PORT=8081
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]