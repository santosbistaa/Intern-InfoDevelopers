FROM openjdk:21

WORKDIR /question

COPY API-Gateway-0.0.1-SNAPSHOT.jar apigateway.jar

EXPOSE 8083

CMD [ "java", "-jar", "apigateway.jar" ]