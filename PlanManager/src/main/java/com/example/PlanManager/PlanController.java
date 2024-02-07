package com.example.PlanManager;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/plans")
public class PlanController {

    private final PlanRepository planRepository;

    private PlanController(PlanRepository planRepository) {
        this.planRepository = planRepository;
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

    @PostMapping
    private ResponseEntity<Void> createPlan(@RequestBody Plan newPlan, UriComponentsBuilder ucb) {
        Plan savedPlan = planRepository.save(newPlan);
        URI locationOfNewCashCard = ucb
                .path("plans/{id}")
                .buildAndExpand(savedPlan.id())
                .toUri();
        return ResponseEntity.created(locationOfNewCashCard).build();
    }
}
