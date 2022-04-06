FROM bellsoft/liberica-openjre-alpine:17
ENV APP_HOME /app
COPY build/libs/*.jar /app/app.jar
ENV JAVA_OPTS=""
WORKDIR $APP_HOME
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar app.jar" ]