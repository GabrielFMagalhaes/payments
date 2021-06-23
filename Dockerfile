FROM openjdk:11-jdk

ENV JAR_NAME payments-challenge-0.0.1-SNAPSHOT.jar

COPY build/libs/$JAR_NAME $JAR_NAME

CMD java -jar $JAR_NAME