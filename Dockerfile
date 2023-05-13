FROM openjdk:17
COPY build/libs/musicLibrary.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]