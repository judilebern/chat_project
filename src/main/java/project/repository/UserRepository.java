package project.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.entities.ChatUser;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<ChatUser, Long> {

    @Query("SELECT u.username FROM ChatUser u WHERE u.userId = :uuid")
    String findUserById(@Param("uuid") UUID uuid);

    @Query("SELECT u.username FROM ChatUser u WHERE u.username = :username")
    String findUserByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO chat_user (user_id, username, is_active, is_admin, user_created_on) VALUES (:user_id, :username, :is_active, :is_admin, :user_created_on) ", nativeQuery = true)
    void createNewUser(@Param("user_id") UUID user_id, @Param("username") String username,
                              @Param("is_active") boolean is_active, @Param("is_admin") boolean is_admin, @Param("user_created_on") LocalDateTime user_created_on);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM chat_user WHERE user_id = :user_id", nativeQuery = true)
    void deleteExistingUser(@Param("user_id") UUID user_id);


}
