package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.MessageRepository;
import project.User;
import project.UserRepository;
import project.requests.CreateNewUserRequest;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

//    @GetMapping("/user/{id}")
//    public String getUser(@PathVariable("id") String id){
//        return id;
//    }
//
//    @GetMapping("/user")
//    public String getSomething(@RequestParam String anotherId){
//        return anotherId;
//    }
//
//    //get metodas niekada neturi RequestBody
//    //post filtravimui
//    @PostMapping("/user")
//    public String getSomething2(@RequestBody User user){
//        return user.getId();
//    }3

    @PostMapping("/newUser")
    public String createNewUser(@RequestBody CreateNewUserRequest request) {
        userRepository.createNewUser(UUID.randomUUID(), request.getUsername(), request.getIsActive(), request.getIsAdmin(), LocalDateTime.now());
        return "Success";
    }
}
