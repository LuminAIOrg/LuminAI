drop table SensorData;
create table SensorData (
                            sd_id bigint not null,
                            timestamp bigint not null,
                            value float(53),
                            s_id bigint,
                            primary key (sd_id, timestamp)
);

SELECT create_hypertable('sensordata', by_range('timestamp'));