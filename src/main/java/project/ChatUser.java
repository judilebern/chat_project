package project;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@Table(name = "chat_user")
public class ChatUser {

    @Id
    private UUID userId;
    private String username;
    private boolean isActive;
    private boolean isAdmin;
    private Timestamp userCreatedOn;
    private Timestamp userDeletedOn;
}
