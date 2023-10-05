-- liquibase formatted sql

-- changeset avolgin:1
CREATE TABLE NotificationTask (
    id  BIGSERIAL,
    time TIMESTAMP,
    chatID BIGINT,
    text VARCHAR,
    flag BOOLEAN,
    PRIMARY KEY(id)
);