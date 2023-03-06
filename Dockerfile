FROM adoptopenjdk/openjdk11
VOLUME /tmp
COPY build/libs/tecst-0.0.1-SNAPSHOT.jar app.jar
CMD java -jar /app.jar