package com.example.cisco.exception;

import com.example.cisco.model.User;

public class NoPhoneFoundException extends RuntimeException {

    public NoPhoneFoundException(User user) {
        super(String.format("User with id {} and name {} has no phone assigned", user.getUserId(), user.getUserName()));
    }
}