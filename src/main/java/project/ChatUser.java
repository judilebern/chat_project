package project;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.NumericBooleanConverter;

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
    private Boolean isActive;
    private Boolean isAdmin;
    private Timestamp userCreatedOn;
    private Timestamp userDeletedOn;
}
