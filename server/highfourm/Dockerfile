FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/highfourm-0.0.1-SNAPSHOT.jar ./highfourm.jar

COPY shell.sh ./shell.sh

RUN chmod +x ./shell.sh

ENTRYPOINT [ "sh", "./shell.sh" ]