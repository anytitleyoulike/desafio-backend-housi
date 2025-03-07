from openjdk:19

WORKDIR /app

COPY ./assets/challenger-1.0.3.jar .
COPY ./src/main/resources/application.properties /app

CMD ["java", "-jar", "challenger-1.0.3.jar"]