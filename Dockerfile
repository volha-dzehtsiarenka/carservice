FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8080
LABEL version="1.0"

ENV APP_DIR=/carservice
ENV APP_NAME=carservice.jar

RUN mkdir $APP_DIR
COPY target/$APP_NAME $APP_DIR

WORKDIR $APP_DIR

CMD java -jar $APP_NAME
