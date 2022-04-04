FROM bellsoft/liberica-openjre-alpine:17
ENV APP_HOME /app
WORKDIR $APP_HOME
COPY organization-service-impl/build/libs/*.jar /app/app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c"]
CMD ["exec java $JAVA_OPTS -jar app.jar"]