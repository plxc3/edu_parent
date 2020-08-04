FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/server_msm.jar server_msm.jar
ENTRYPOINT ["java","-jar","/server_msm.jar", "&"]