# Verwende das offizielle OpenJDK-Image als Basis
FROM openjdk

# Setze das Arbeitsverzeichnis im Container
WORKDIR /app

# Kopiere die JAR-Datei der Spring Boot-Anwendung ins Arbeitsverzeichnis
COPY target/batch-processing-complete-0.0.1-SNAPSHOT.jar /app/

# Setze den Befehl, der beim Start des Containers ausgeführt wird
CMD ["java", "-jar", "batch-processing-complete-0.0.1-SNAPSHOT.jar"]
