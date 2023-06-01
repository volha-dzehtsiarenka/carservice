FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8080
LABEL version="1.0"

ENV APP_DIR=/carservice
ENV APP_NAME=carservice.jar

COPY src/main/resources/db/changelog/db.changelog.yml /app/resources/db/changelog/db.changelog.yml
COPY target/$APP_NAME $APP_DIR/$APP_NAME

WORKDIR $APP_DIR

CMD java -jar $APP_NAME