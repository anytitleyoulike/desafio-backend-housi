from openjdk:19

WORKDIR /app

COPY ./assets/challenger-0.0.6.jar .
COPY ./src/main/resources/application.properties /app

CMD ["java", "-jar", "challenger-0.0.6.jar"]