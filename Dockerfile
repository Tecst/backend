FROM adoptopenjdk/openjdk11
VOLUME /tmp
#ARG JAR_FILE
COPY build/libs/tecst-0.0.1-SNAPSHOT.jar app.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
CMD java -jar /app.jar