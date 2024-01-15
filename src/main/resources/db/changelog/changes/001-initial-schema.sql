-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE chat_user (
  user_id VARCHAR(36) PRIMARY KEY,
  username VARCHAR(225),
  is_active BOOLEAN,
  is_admin BOOLEAN DEFAULT false,
  user_created_on TIMESTAMP,
  user_deleted_on TIMESTAMP
);

CREATE TABLE messages (
  message_id VARCHAR(36) PRIMARY KEY,
  user_id VARCHAR(36),
  message_text VARCHAR(225),
  message_created_on TIMESTAMP,
  message_updated_on TIMESTAMP,
  FOREIGN KEY (user_id)
      REFERENCES chat_user (user_id)
);