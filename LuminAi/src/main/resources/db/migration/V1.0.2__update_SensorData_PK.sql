drop table SensorData;
create table SensorData (
                            timestamp bigint not null,
                            value float(53),
                            s_id bigint,
                            primary key (timestamp, s_id)
);
