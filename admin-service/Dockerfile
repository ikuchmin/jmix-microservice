FROM openjdk:17-jre-alpine
ENV APP_HOME /app
ADD build/libs/*.jar /app/app.jar
ENV JAVA_OPTS=""
WORKDIR $APP_HOME
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar app.jar" ]