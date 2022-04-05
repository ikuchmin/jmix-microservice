FROM bellsoft/liberica-openjre-alpine:17
ENV APP_HOME /app
WORKDIR $APP_HOME
COPY department-service-impl/build/libs/*.jar /app/app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 $JAVA_OPTS -jar app.jar"]