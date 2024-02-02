package com.example.PlanManager;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plans")
public class PlanController {

    private final PlanRepository planRepository;

    private PlanController(PlanRepository planRepository){
        this.planRepository=planRepository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Plan> findById(@PathVariable Long requestedId){
        if (requestedId.equals(99L)){ 
            Plan plan = new Plan(99L, "First Plan");
            return ResponseEntity.ok(plan);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
