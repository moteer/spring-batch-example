DROP TABLE Account IF EXISTS;

CREATE TABLE Account (
    id   INT PRIMARY KEY AUTO_INCREMENT,
    Bank        VARCHAR(255),
    Kontonummer VARCHAR(20),
    Saldo       DECIMAL(10, 2)
);

INSERT INTO Account (Bank, Kontonummer, Saldo)
VALUES ('Sparkasse', '1234567890', 1000.00),
       ('Commerzbank', '9876543210', 1500.00),
       ('Deutsche Bank', '5432109876', 2000.00),
       ('Volksbank', '1357924680', 500.00),
       ('Postbank', '2468013579', 3000.00);

