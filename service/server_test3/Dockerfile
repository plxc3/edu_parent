FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/server_test3.jar server_test3.jar
ENTRYPOINT ["java","-jar","/server_test3.jar", "&"]
