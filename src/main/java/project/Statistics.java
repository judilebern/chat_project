package project;

import java.time.LocalDateTime;

public interface Statistics {
    String getUsername();
    Integer getMessageCount();
    LocalDateTime getFirstMessageDate();
    LocalDateTime getLastMessageDate();
    Double getAvgMessageLength();
    String getLastMessageText();

}
