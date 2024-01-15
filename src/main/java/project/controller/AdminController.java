package project.controller;

import org.springframework.web.bind.annotation.*;
import project.User;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") String id){
        return id;
    }

    @GetMapping("/user")
    public String getSomething(@RequestParam String anotherId){
        return anotherId;
    }

    //get metodas niekada neturi RequestBody
    //post filtravimui
    @PostMapping("/user")
    public String getSomething2(@RequestBody User user){
        return user.getId();
    }
}
