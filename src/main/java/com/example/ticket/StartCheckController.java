package com.example.ticket;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartCheckController {

    @GetMapping("/api/Start")
    public String StartCheck(){
        return "🔥 Ticket API Server is perfectly running! 🔥";
    }
}
