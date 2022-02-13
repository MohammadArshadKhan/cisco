package com.example.cisco;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class Utility {
    public static void main(String[] args) {
        String x= "Arsaddc's Pixel";
        Optional<String> user= Arrays.stream(x.split(" ")).findFirst();
        String userName= user.get().substring(0, user.get().length()-2);
        System.out.println(userName);
    }
}
