package project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<ChatUser, Long> {

    @Query("SELECT u.username FROM ChatUser u WHERE u.userId = :uuid")
    String findUserById(@Param("uuid") UUID uuid);
}
