package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.ApplicationException;
import project.entities.Statistics;
import project.repository.MessageRepository;
import project.repository.UserRepository;
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

    @PostMapping("/user")
    public HttpStatus createNewUser(@RequestBody CreateNewUserRequest request) {
        if (userRepository.findUserByUsername(request.getUsername().toUpperCase()) != null) {
            throw new ApplicationException(
                    "user-exists",
                    String.format("User with username=%s is already exists", request.getUsername()),
                    HttpStatus.CONFLICT
            );
        }
        userRepository.createNewUser(UUID.randomUUID(), request.getUsername().toUpperCase(), request.getIsActive(), request.getIsAdmin(), LocalDateTime.now());
        return HttpStatus.OK;
    }

    @DeleteMapping("/user")
    public HttpStatus deleteExistingUser(@RequestParam UUID userID) {
        if (userRepository.findUserById(userID) == null) {
            throw new ApplicationException(
                    "user-not-found",
                    String.format("User with id=%s not found", userID),
                    HttpStatus.NOT_FOUND
            );
        }
        messageRepository.updateMessageUserToAnonymous(userID, LocalDateTime.now());
        userRepository.deleteExistingUser(userID);
        return HttpStatus.OK;
    }

    @GetMapping("/statistics")
    public List<Statistics> getStatistics() {
        return messageRepository.getStatistics();
    }
}
