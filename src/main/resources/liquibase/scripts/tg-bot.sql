-- liquibase formatted sql

-- changeset avolgin:1
CREATE TABLE notification_task (
    id  SERIAL,
    time TIMESTAMP,
    chatID INTEGER,
    text VARCHAR,
    flag BOOLEAN,
    PRIMARY KEY(id)
);