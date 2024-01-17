package project;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

public interface MessagesUserResponse {

    String getUsername();
    String getMessageText();
    LocalDateTime getMessageCreatedOn();

}
