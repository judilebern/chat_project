package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.*;
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

    //gaunamos visos žinutės
    @GetMapping(value = "/message")
    public List<Messages> getMessage() {
        return messageRepository.getAllMessages();
    }


    @PostMapping(value = "/message/{userId}")
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
