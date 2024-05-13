FROM registry.access.redhat.com/ubi8/openjdk-17:1.16

ENV LANGUAGE='en_US:en'
ARG DB_USERNAME
ARG DB_PASS
ARG DB_NAME
ARG MQTT_CONNECTOR_TEST
ARG MQTT_HOST
ARG MQTT_PORT
ARG MQTT_USERNAME
ARG MQTT_PASSWORD

ENV DB_USERNAME=$DB_USERNAME
ENV DB_PASS=$DB_PASS
ENV DB_NAME=$DB_NAME
ENV MQTT_CONNECTOR_TEST=$MQTT_CONNECTOR_TEST
ENV MQTT_HOST=$MQTT_HOST  
ENV MQTT_PORT=$MQTT_PORT
ENV MQTT_USERNAME=$MQTT_USERNAME
ENV MQTT_PASSWORD=$MQTT_PASSWORD

COPY --chown=185 target/quarkus-app/lib/ /deployments/lib/
COPY --chown=185 target/quarkus-app/*.jar /deployments/
COPY --chown=185 target/quarkus-app/app/ /deployments/app/
COPY --chown=185 target/quarkus-app/quarkus/ /deployments/quarkus/

COPY log-env.sh /opt/jboss/container/java/run/log-env.sh

EXPOSE 8080
USER 185

ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Dquarkus.profile=prod"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"

ENTRYPOINT ["/opt/jboss/container/java/run/log-env.sh", "/opt/jboss/container/java/run/run-java.sh" ]

