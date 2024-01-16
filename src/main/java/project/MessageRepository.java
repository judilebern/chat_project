package project;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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

//    @Query("""
//            SELECT
//                ms.chatUserId.userId,
//                COUNT(ms.messageId) as message_count,
//                MIN(ms.messageCreatedOn) as first_message,
//                MAX(ms.messageCreatedOn) as last_message,
//                AVG(LENGTH(ms.messageText)) as avg_message_length
//            FROM Messages ms
//            WHERE ms.chatUserId.userId = :chatUserId
//            GROUP BY ms.chatUserId.userId
//            """)
//    @Query("""
//            SELECT
//              new project.Statistics(ms.messageText,
//                ms.messageCreatedOn)
//            FROM Messages ms
//            WHERE ms.chatUserId.userId = :chatUserId
//            """)

    //TODO do this exactly like here just with correct select
@Query("""
            SELECT
                ms.messageText as messageText,
                ms.messageCreatedOn as messageCreatedOn,
                cu.username as username
            FROM Messages ms
            LEFT JOIN ChatUser cu ON cu.userId = ms.chatUserId.userId
            WHERE ms.chatUserId.userId = :chatUserId
            """)
    public List<TEST> getStatistics(@Param("chatUserId") UUID chatUserId);


/*
    SELECT
    cu.username,
    COUNT(ms.message_id) as message_count,
    MIN(ms.message_created_on) as first_message,
    MAX(ms.message_created_on) as last_message,
    AVG(LENGTH(ms.message_text)) as AVG_message_length,
    (SELECT ms2.message_text FROM messages ms2 WHERE ms2.user_id = cu.user_id ORDER BY  ms2.message_created_on desc LIMIT 1) as last_message_text
    FROM chat_user cu
    LEFT JOIN messages ms ON ms.user_id = cu.user_id
    GROUP BY cu.user_id, cu.username;
*/

}
