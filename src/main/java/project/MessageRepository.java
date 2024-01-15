package project;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Messages, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Messages (message_id, user_id, message_text, message_created_on) VALUES (:messageId, :chatUserId, :messageText, :messageCreatedOn) ", nativeQuery = true)
    public void createNewMessage(@Param("messageId") UUID messageId, @Param("chatUserId") UUID chatUserId,
                                 @Param("messageText") String messageText, @Param("messageCreatedOn") LocalDateTime messageCreatedOn);
}
