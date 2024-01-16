package project.controller;

import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.Car;
import project.MessageRepository;
import project.Messages;
import project.UserRepository;
import project.requests.CreateMessageRequest;

import java.sql.Timestamp;
import java.time.DateTimeException;
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

    //sukuriama nauja žinutė
    //todo kai useris neegzistuoja nemest sql error. DO SOME CUSTOM WORK
    @PostMapping(value = "/message/{userId}")
    public String createMessage(@RequestBody CreateMessageRequest request) {
        messageRepository.createNewMessage(UUID.randomUUID(), request.getUserId(), request.getMessage(), LocalDateTime.now());
        return "Success";
    }
}
