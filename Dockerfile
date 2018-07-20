FROM openjdk:alpine

RUN mkdir -p /opt/dyescape/resource-check
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /opt/dyescape/resource-check/ResourceCheck.jar" ]
EXPOSE 8080

COPY ./target/ResourceCheck.jar /opt/dyescape/resource-check/ResourceCheck.jar

RUN touch /ResourceCheck.jar
