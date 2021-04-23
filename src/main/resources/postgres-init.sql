
--
-- Первоначальное создание таблиц
--

DROP TABLE IF EXISTS resources;

CREATE TABLE IF NOT EXISTS resources
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    categoryName VARCHAR (255)
    availableName VARCHAR (255),
    localisedName VARCHAR (255),
    amount INT,
    sellPrice DOUBLE,
    sellOffers INT,
    buyPrice DOUBLE,
    buyOrders INT,
    );

DROP TABLE if EXISTS weapons;

CREATE TABLE if NOT EXISTS weapons
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    categoryName VARCHAR (255)
    availableName VARCHAR (255),
    localisedName VARCHAR (255),
    amount INT,
    sellPrice DOUBLE,
    sellOffers INT,
    buyPrice DOUBLE,
    buyOrders INT,
    );