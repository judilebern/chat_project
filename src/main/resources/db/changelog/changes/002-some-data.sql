-- liquibase formatted sql

-- changeset liquibase:2
CREATE TABLE company (
  id INT PRIMARY KEY,
  name VARCHAR(50),
  company_code INT,
  description VARCHAR(250)
);