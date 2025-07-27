FROM openjdk:21

WORKDIR /question

COPY Question-Service-0.0.1-SNAPSHOT.jar question.jar

EXPOSE 9092

CMD [ "java", "-jar", "question.jar" ]