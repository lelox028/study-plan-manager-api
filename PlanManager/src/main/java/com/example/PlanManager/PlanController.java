package com.example.PlanManager;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plans")
public class PlanController {

    @GetMapping("/{requestedID}")
    private ResponseEntity<String> findById(){
        return ResponseEntity.ok("{}");
    }
}
