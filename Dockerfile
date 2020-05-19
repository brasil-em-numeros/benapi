FROM azul/zulu-openjdk-alpine:13-jre
MAINTAINER Juliano Alves <von.juliano@gmail.com>

RUN mkdir -p /app
WORKDIR /app

COPY ./target/scala-2.13/benapi.jar ./ben.mv.db ./

CMD exec java -jar benapi.jar