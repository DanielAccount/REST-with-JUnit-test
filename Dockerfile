FROM docker.io/eclipse-temurin:21-jdk

WORKDIR /app

# SNAPSHOT JAR will be copied an rplace to calculator .jar(custom name)
ADD target/demo-0.0.1-SNAPSHOT.jar calculator.jar


EXPOSE 9091

# Run the application
ENTRYPOINT ["java", "-jar", "calculator.jar"]
