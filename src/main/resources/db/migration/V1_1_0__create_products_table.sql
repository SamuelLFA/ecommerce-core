CREATE TABLE IF NOT EXISTS products (
    id UUID NOT NULL PRIMARY KEY,
    description varchar(255),
    value DECIMAL
);