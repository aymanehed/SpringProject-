create file docker
from eclipse-temurin:17-jdk-alpine
volume /tmp
copy target/*.jar app.jar
entrypoint ["java","-jar","/app.jar"]