CREATE TABLE users
(
    id IDENTITY NOT NULL,
    username   VARCHAR(50)  NOT NULL,
    password   VARCHAR(100) NOT NULL,
    fio VARCHAR(100) NOT NULL,
    email      VARCHAR(100),
    PRIMARY KEY (id)
);