package project.controller;

import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.Car;
import project.MessageRepository;
import project.UserRepository;
import project.requests.CreateMessageRequest;

import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping(value = "/message/{id}")
    public String getMessage(@PathVariable("id") UUID id) {
        return userRepository.findUserById(id);
    }


//    @PostMapping(value = "/message/{userId}")
//    public String createMessage(@PathVariable("userId") UUID userId, @RequestParam String message) {
//        messageRepository.createNewMessage(UUID.randomUUID(), userId, message, LocalDateTime.now());
//        return "DONE";
//    }

    @PostMapping(value = "/message/{userId}")
    public String createMessage(@RequestBody CreateMessageRequest request) {
        messageRepository.createNewMessage(UUID.randomUUID(), request.getUserId(), request.getMessage(), LocalDateTime.now());
        return "DONE";
    }
}
