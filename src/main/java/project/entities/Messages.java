package project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@Table(name = "messages")
public class Messages {

    @Id
    private UUID messageId;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private ChatUser chatUserId;

    private String messageText;

    private Timestamp messageCreatedOn;

    private Timestamp messageUpdatedOn;
}
