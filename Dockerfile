from openjdk:17

WORKDIR /app

COPY ./target/challenger-0.0.4.jar .
COPY ./src/main/resources/application.properties /app

CMD ["java", "-jar", "challenger-0.0.4.jar"]