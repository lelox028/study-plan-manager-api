package com.example.PlanManager;

import org.springframework.data.annotation.Id;

record Plan(@Id Long id, String name) {

}
