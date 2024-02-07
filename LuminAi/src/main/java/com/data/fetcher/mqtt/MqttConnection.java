package com.data.fetcher.mqtt;

import com.data.model.Group;
import com.data.model.Sensor;
import com.data.model.SensorData;
import com.data.spi.FetcherType;
import com.data.spi.ServiceInterface;
import com.data.utils.PropLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import io.quarkus.runtime.annotations.RegisterForReflection;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

@ApplicationScoped
@Blocking
@RegisterForReflection
public class MqttConnection implements ServiceInterface {

    private Properties properties;

    private BehaviorSubject<SensorData> subject;

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

    public void consume(String topicName, MqttMessage message){
        ObjectMapper objectMapper = new ObjectMapper();
        String[] topicNameSplit = topicName.split("/");
        String sensorName = topicNameSplit[topicNameSplit.length-2];
        String groupName = topicNameSplit[topicNameSplit.length-3];

        //This unit setting method is temporary
        //sensor.setUnit("°");
        try {

            ParserDto dataObject = objectMapper.readValue(message.getPayload(), ParserDto.class);

            //create SensorData
            SensorData newSensorData = new SensorData();
            newSensorData.setValue(dataObject.value());
            newSensorData.setTimeStamp(dataObject.timestamp());

            //create Sensor
            Sensor newSensor = new Sensor(sensorName);
            newSensor.addValue(newSensorData);

            //create group
            Group newGroup = new Group();
            newGroup.setName(groupName);

            //set connections
            newSensor.setGroup(newGroup);
            newSensorData.setDevice(newSensor);
            newGroup.addSensor(newSensor);

            subject.onNext(newSensorData);
        }catch (Exception e){
            Log.warn("This format is not excepted: " + e.getMessage());
        }

    }

    public void invoke()  {
        PropLoader propLoader = new PropLoader();
        propLoader.setType(FetcherType.MQTT);

        this.properties = propLoader.getProperties();


        System.out.println(this.properties.getProperty("host"));

        String publisherId = "g:luminai";
        //Todo Only Start when this method is invoked
        try(MqttClient client = new MqttClient(String.format("tcp://%s:%s", this.properties.getProperty("host"), this.properties.getProperty("port")), publisherId, new MemoryPersistence())) {
            CountDownLatch latch = new CountDownLatch(30);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(this.properties.getProperty("username").toString());
            options.setPassword(this.properties.getProperty("password").toString().toCharArray());
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


            client.subscribe(properties.getProperty("topic"), qos);

            latch.await();

        } catch (MqttException e){
            throw new IllegalArgumentException("an error occured while connecting: " + e);
        } catch (InterruptedException e) {
            throw new RuntimeException("an error occured while subscribing: " + e);
        }
    }

    @Override
    public FetcherType getType() {
        return FetcherType.MQTT;
    }

    @Override
    public void setSubject(BehaviorSubject<SensorData> subject) {
        this.subject = subject;
    }
}
