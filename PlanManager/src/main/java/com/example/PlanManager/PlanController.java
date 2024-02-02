package com.example.PlanManager;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/plans")
public class PlanController {

    private final PlanRepository planRepository;

    private PlanController(PlanRepository planRepository){
        this.planRepository=planRepository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Plan> findById(@PathVariable Long requestedId) {
        Optional<Plan> planOptional = planRepository.findById(requestedId);
        if (planOptional.isPresent()) {
            return ResponseEntity.ok(planOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
