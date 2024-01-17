package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
