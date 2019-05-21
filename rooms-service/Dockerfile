FROM openjdk:12

VOLUME /tmp
ADD target/rooms-service.jar rooms-service.jar

EXPOSE 8084
ENTRYPOINT ["java", "-jar", "/rooms-service.jar"]