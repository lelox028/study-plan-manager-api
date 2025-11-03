# Base: OpenJDK 21
FROM openjdk:21-jdk-slim

# Crear directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el contenido del backend al contenedor
COPY StudyPlanManagerApi/ .

# Build con Maven (usa el wrapper incluido)
RUN ./mvnw clean package -DskipTests

# Exponer un puerto (opcional, Spring Boot usará ${PORT})
EXPOSE 8080

# Comando para iniciar la app (especifica el JAR exacto para evitar problemas con wildcard)
CMD ["java", "-jar", "target/StudyPlanManagerApi-0.0.1-SNAPSHOT.jar"]
