FROM openjdk:8
EXPOSE 8080
ADD target/aplazo-test.jar aplazo-test.jar
ENTRYPOINT ["java", "-jar", "/aplazo-test.jar"]