FROM openjdk:15
VOLUME /tmp
COPY target/icarros-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8282
ENTRYPOINT ["java", "-jar", "/app.jar"]