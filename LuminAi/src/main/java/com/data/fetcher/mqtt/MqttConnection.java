package com.data.fetcher.mqtt;

import com.data.dto.DataDto;
import com.data.fetcher.DataFetcher;
import com.data.model.Sensor;
import com.data.model.SensorData;
import com.data.repository.SensorDataRepository;
import com.data.repository.SensorRepository;
import com.data.session.DataSocket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import io.quarkus.runtime.annotations.RegisterForReflection;
import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

@ApplicationScoped
@Blocking
@RegisterForReflection
public class MqttConnection implements DataFetcher {
    final String topicWildcard = "eg/#";
    @Inject
    DataSocket clients;
    @Inject
    SensorDataRepository dataRepository;

    @Inject
    SensorRepository sensorRepository;

    @ConfigProperty(name = "mqtt.host")
    String host;
    @ConfigProperty(name = "mqtt.port")
    String port;
    @ConfigProperty(name = "mqtt.username")
    String username;
    @ConfigProperty(name = "mqtt.password")
    String password;

    @Inject
    @ConfigProperty(name = "haxi")
    public String haxi;

    int qos = 0;

    public CompletableFuture<Void> invokeAsync() {
        System.out.println("Invoked MQTT Connection asynchronously");

        return CompletableFuture.runAsync(() -> {
            try {
                invoke();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    @Transactional

    public void consume(String topicName, MqttMessage message) throws InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String[] topicNameSplit = topicName.split("/");
            Sensor sensor = sensorRepository.createOrGetSensor(topicNameSplit[topicNameSplit.length-2]);

            //This unit setting method is temporary
            sensor.setUnit("°");

            SensorData sensorData = new SensorData();
            sensorData.setDevice(sensor);

            DataDto dataObject = objectMapper.readValue(message.getPayload(), DataDto.class);
            sensorData.setTimeStamp(dataObject.timestamp());
            sensorData.setValue(dataObject.value());

            //dataRepository.addData(sensorData);

            clients.publish(sensorData);

        } catch (JsonProcessingException e) {
            Log.error("error parsing into Data Object: " + message);
        } catch (IOException e) {
            throw new InterruptedException(e.getMessage());
        }
    }

    @Override
    public void invoke()  {
        String publisherId = "g:luminai";
        //Todo Only Start when this method is invoked
        try(MqttClient client = new MqttClient(String.format("tcp://%s:%s", this.host, this.port), publisherId, new MemoryPersistence())) {
            CountDownLatch latch = new CountDownLatch(30);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(this.username);
            options.setPassword(this.password.toCharArray());
            options.setConnectionTimeout(60);
            options.setKeepAliveInterval(60);

            //set callback
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {
                    System.out.println("connection lost: " + throwable.getMessage());
                    latch.countDown();
                }

                @Override
                public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                    System.out.println("topic: " + topic);
                    System.out.println("Qos: " + mqttMessage.getQos());
                    System.out.println("message content: " + new String(mqttMessage.getPayload()));
                    //TODO: add consume
                    consume(topic, mqttMessage);
                    latch.countDown();
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    System.out.println("deliveryComplete---------" + iMqttDeliveryToken.isComplete());
                }
            });

            client.connect(options);


            client.subscribe(topicWildcard, qos);

            latch.await();

        } catch (MqttException e){
            throw new IllegalArgumentException("an error occured while connecting: " + e);
        } catch (InterruptedException e) {
            throw new RuntimeException("an error occured while subscribing: " + e);
        }
    }
}
