FROM openjdk:11-jre-slim
ARG JAR_FILE=./target/fota-0.0.1.jar
RUN mkdir /var/files
COPY ${JAR_FILE} fota-0.0.1.jar

# Expose port 8080 to the Docker host, so we can access it
# from the outside.
EXPOSE 8080
ENTRYPOINT ["java","-jar","/fota-0.0.1.jar"]
