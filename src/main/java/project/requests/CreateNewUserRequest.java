package project.requests;

import lombok.Data;

@Data
public class CreateNewUserRequest {

    private String username;
    private Boolean isActive;
    private Boolean isAdmin;

}
