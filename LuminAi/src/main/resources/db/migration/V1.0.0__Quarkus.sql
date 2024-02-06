create table Sensor (
                        s_id bigint not null,
                        name varchar(255),
                        unit varchar(255),
                        g_id bigint,
                        primary key (s_id)
);

create table SensorData (
                            sd_id bigint not null,
                            timestamp bigint not null,
                            value float(53),
                            s_id bigint,
                            primary key (sd_id)
);

create table sensorGroup (
                             g_id bigint not null,
                             name varchar(255),
                             id bigint,
                             primary key (g_id)
);

create sequence Sensor_SEQ start with 1 increment by 50;

create sequence SensorData_SEQ start with 1 increment by 50;

create sequence sensorGroup_SEQ start with 1 increment by 50;

alter table if exists Sensor
    add constraint FKfciu0rfmd37sx49pej7ap30cx
        foreign key (g_id)
            references sensorGroup;

alter table if exists SensorData
    add constraint FK5a5ilfgj2u1pim423px0jno3b
        foreign key (s_id)
            references Sensor;

alter table if exists sensorGroup
    add constraint FKnape8qss5weyhk6wvtbvo81og
        foreign key (id)
            references sensorGroup;