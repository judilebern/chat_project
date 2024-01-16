package project.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateNewUserRequest {

    private String username;
    private Boolean isActive;
    private Boolean isAdmin;

}
