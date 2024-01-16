package project;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Messages, Long> {

    @Query("SELECT m FROM Messages m ORDER BY m.messageCreatedOn DESC")
    public List<Messages> getAllMessages();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Messages (message_id, user_id, message_text, message_created_on) VALUES (:messageId, :chatUserId, :messageText, :messageCreatedOn) ", nativeQuery = true)
    public void createNewMessage(@Param("messageId") UUID messageId, @Param("chatUserId") UUID chatUserId,
                                 @Param("messageText") String messageText, @Param("messageCreatedOn") LocalDateTime messageCreatedOn);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Messages SET user_id = '0b97b1e4-db82-4739-8112-42fe01bc3544', message_updated_on = :messageUpdatedOn WHERE user_id = :chatUserId", nativeQuery = true)
    public void updateMessageUserToAnonymous(@Param("chatUserId") UUID chatUserId, @Param("messageUpdatedOn") LocalDateTime messageUpdatedOn);

@Query("""
            SELECT
                cu.username as username,
                COUNT(ms.messageId) as messageCount,
                MIN(ms.messageCreatedOn) as firstMessageDate,
                MAX(ms.messageCreatedOn) as lastMessageDate,
                AVG(LENGTH(ms.messageText)) as avgMessageLength,
                (SELECT ms2.messageText FROM Messages ms2 WHERE ms2.chatUserId.userId = cu.userId ORDER BY  ms2.messageCreatedOn desc LIMIT 1) as lastMessageText
                FROM ChatUser cu
                LEFT JOIN Messages ms ON ms.chatUserId.userId = cu.userId
                GROUP BY cu.userId, cu.username
            """)
    public List<Statistics> getStatistics();

}
