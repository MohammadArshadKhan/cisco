package com.example.cisco.exception;

import com.example.cisco.model.User;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(UUID uuid) {

        super(String.format("User with id {} and name {} not found",uuid));
    }
}