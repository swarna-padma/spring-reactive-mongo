FROM openjdk:8
# target location of jar
ADD target/reactive-mongo-poc.jar app.jar
# Command to execute the jar
ENTRYPOINT ["java","-jar","app.jar"]

# FROM openjdk:8
# LABEL maintainer="cisionpoc"
# VOLUME /main-app
# ADD build/libs/reactive-mongo-poc-0.0.1-SNAPSHOT.jar app.jar
# EXPOSE 8080
# ENTRYPOINT ["java", "-jar","/app.jar"]