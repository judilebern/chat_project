-- liquibase formatted sql

-- changeset liquibase:2
INSERT INTO chat_user
VALUES
('0b97b1e4-db82-4739-8112-42fe01bc3544', 'ANONYMOUS', true, true, '2023-12-31 00:00:00.000', null),
('b8fa450a-61b0-493c-918c-3a96ba87aa06', 'ADMIN', true, true, '2023-12-31 00:00:00.000', null),
('d0ca0df5-5eb7-4079-9579-8844f1d43c8e', 'USERISTEST', true, false, '2023-12-31 00:00:00.000', null),
('f9f17eed-c308-4e03-9ed5-d9268bb1afd8', 'TESTTEST', true, false, '2023-12-31 00:00:00.000', null);

INSERT INTO messages
VALUES
('521e5c94-9e2e-44a9-9239-877edf4636b0', 'b8fa450a-61b0-493c-918c-3a96ba87aa06', 'HELLO WORLD', '2023-01-16T08:23:52.912+00:00', null),
('00bb54b2-4cf1-451b-8a49-983151493e59', 'b8fa450a-61b0-493c-918c-3a96ba87aa06', 'testtt', '2024-01-15T08:23:52.912+00:00', null),
('dbfa35a6-3cad-472d-9102-1f724f2bc029', 'd0ca0df5-5eb7-4079-9579-8844f1d43c8e', 'Iam USERISTEST', '2024-01-16T08:23:52.912+00:00', null),
('29946b24-752b-4b0b-be0b-7dc452e0609e', 'd0ca0df5-5eb7-4079-9579-8844f1d43c8e', 'HI HI HI USERISTEST', '2023-05-16T08:23:52.912+00:00', null);