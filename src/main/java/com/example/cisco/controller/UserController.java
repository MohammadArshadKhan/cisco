package com.example.cisco.controller;

import com.example.cisco.model.User;
import com.example.cisco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping(path="/", produces = "application/json")
    public Collection<User> getAll() {
        logger.info("This function will return all the users");
        return userService.getAllUsers();
    }

    @PostMapping(produces = "application/json")
    public User createUser(@RequestBody User user) {
        logger.info("Create a user {} ", user);
        return userService.addUser(user);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable("id") UUID id) {
        userService.deleteUser(id);
    }

    @PutMapping(path = "{id}")
    public void updateUser(@PathVariable("id") @RequestBody User user) {
        userService.updatePreferredPhone(user);
    }

}
