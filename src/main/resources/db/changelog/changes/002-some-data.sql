-- liquibase formatted sql

-- changeset liquibase:2
INSERT INTO chat_user
VALUES
('b8fa450a-61b0-493c-918c-3a96ba87aa06', 'ADMIN', true, true, '2023-12-31 00:00:00.000', null);

INSERT INTO messages
VALUES
('521e5c94-9e2e-44a9-9239-877edf4636b0', 'b8fa450a-61b0-493c-918c-3a96ba87aa06', 'HELLO WORLD', '2023-12-31 00:00:00.000', null);