package project.entities;

import java.time.LocalDateTime;

public interface MessagesUserResponse {

    String getUsername();

    String getMessageText();

    LocalDateTime getMessageCreatedOn();

}
