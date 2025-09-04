FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/NotesApp-0.0.1-SNAPSHOT.jar notesapp-v1.0.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","notesapp-v1.0.jar"]