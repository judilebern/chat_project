package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.ApplicationException;
import project.entities.MessagesUserResponse;
import project.repository.MessageRepository;
import project.repository.UserRepository;
import project.requests.CreateMessageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping(value = "/message")
    public List<MessagesUserResponse> getMessage() {
        return messageRepository.getAllMessages();
    }


    @PostMapping(value = "/message")
    public HttpStatus createMessage(@RequestBody CreateMessageRequest request) {
        if (userRepository.findUserById(request.getUserId()) == null) {
            throw new ApplicationException(
                    "user-not-found",
                    String.format("User with id=%s not found", request.getUserId()),
                    HttpStatus.NOT_FOUND
            );
        }
        messageRepository.createNewMessage(UUID.randomUUID(), request.getUserId(), request.getMessage(), LocalDateTime.now());
        return HttpStatus.OK;
    }
}
