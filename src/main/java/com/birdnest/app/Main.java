package com.birdnest.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
        System.out.println("Dev beginning");
    }
    @GetMapping("/greet")
    public String greet(){
        return "greet";
    }
    @GetMapping("/drone")
    public String drone(){
        return "greet";
    }
    @GetMapping("/position")
    public String position(){
        return "greet";
    }
}
