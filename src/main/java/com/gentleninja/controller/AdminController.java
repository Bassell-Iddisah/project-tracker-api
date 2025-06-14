package com.gentleninja.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Welcome to the Admin Dashboard";
    }

    @PostMapping("/create-announcement")
    public String createAnnouncement(@RequestBody String message) {
        // Stub logic
        return "Announcement created: " + message;
    }
}

