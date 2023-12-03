FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY build/libs/*.jar orchestrator.jar
ENTRYPOINT ["java", "-Xmx800m", "-XX:+UseG1GC", "-jar", "/orchestrator.jar"]
