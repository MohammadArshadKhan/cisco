package com.example.cisco.model;

import lombok.*;
import java.util.UUID;

@Data
@AllArgsConstructor

public class User {
    private UUID userId;
    private String userName;
    private String password;
    private String emailAddress;
    private String preferredPhoneNumber;
}
