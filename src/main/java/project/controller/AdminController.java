package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.*;
import project.requests.CreateNewUserRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("/newUser")
    public String createNewUser(@RequestBody CreateNewUserRequest request) {
        userRepository.createNewUser(UUID.randomUUID(), request.getUsername().toUpperCase(), request.getIsActive(), request.getIsAdmin(), LocalDateTime.now());
        return "Success";
    }

    @PostMapping("/deleteUser")
    public String deleteExistingUser(@RequestParam UUID userID) {
        messageRepository.updateMessageUserToAnonymous(userID, LocalDateTime.now());
        userRepository.deleteExistingUser(userID);
        return "Success";
    }

    @GetMapping("/statistics")
    public List<Statistics> getStatistics() {
        return messageRepository.getStatistics();
    }
}
