CREATE TABLE IF NOT EXISTS products (
    id UUID NOT NULL PRIMARY KEY,
    info varchar(255),
    price DECIMAL
);