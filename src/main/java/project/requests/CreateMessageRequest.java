package project.requests;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateMessageRequest {

    private UUID userId;
    private String message;

}
