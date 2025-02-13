CREATE TABLE todo
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    user_id     INT NOT NULL,
    title   VARCHAR(20) NOT NULL,
    contents    VARCHAR(500),
    date       DATETIME,
    update_date DATETIME,
    deleted   BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE user
(
    id         int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    user_name       VARCHAR(20) NOT NULL,
    email      VARCHAR(50) UNIQUE ,
    password    VARCHAR(200) NOT NULL,
    date   DATETIME,
    update_date DATETIME,
    deleted   BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE comments
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    contents    VARCHAR(500),
    user_id     INT NOT NULL,
    todo_id     INT NOT NULL,
    date       DATETIME,
    update_date DATETIME,
    deleted   BOOLEAN NOT NULL DEFAULT FALSE
);