CREATE TABLE serviceInstance (
                                  id INT PRIMARY KEY,
                                  name VARCHAR(255),
                                  serviceName VARCHAR(255)
);

create sequence serviceinstance_seq start with 1 increment by 1;