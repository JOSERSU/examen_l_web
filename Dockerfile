FROM openjdk:21.0.5-jdk
COPY target/biblioteca-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]