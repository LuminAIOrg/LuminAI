CREATE TABLE driver (
                                  id int primary key ,
                                  name VARCHAR(255) unique 
);

create sequence driver_seq start with 1 increment by 1;

ALTER TABLE serviceInstance
DROP COLUMN serviceName;

ALTER TABLE serviceInstance
    ADD COLUMN serviceId INT;

ALTER TABLE serviceInstance
    ADD CONSTRAINT fk_service
        FOREIGN KEY (serviceId) REFERENCES driver(id);