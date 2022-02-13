package com.example.cisco.model;

import lombok.*;
import java.util.UUID;

@Data
public class Phone {

    private UUID userId;
    private String phoneName;
    private String phoneNumber;
    private PhoneModel model;
}
