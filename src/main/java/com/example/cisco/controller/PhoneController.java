package com.example.cisco.controller;

import com.example.cisco.model.Phone;
import com.example.cisco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/phone")
public class PhoneController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "{id}", produces = "application/json")
    public List<Phone> getAllPhoneUser(@PathVariable("id") UUID id) {
        return userService.getAllUserPhone(id);
    }

    @PostMapping
    public void addPhone(@RequestBody Phone phone ) {
        userService.addPhoneToUser(phone);
    }

    @DeleteMapping(path = "{id}")
    public void deletePhone(@PathVariable("name") UUID uuid) {
        userService.deletePhone(uuid);
    }
}
