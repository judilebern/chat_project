package project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.Car;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping(value = "/message")
    public Car getMainPage() {
        Car car = new Car();
        return car;
    }
}
