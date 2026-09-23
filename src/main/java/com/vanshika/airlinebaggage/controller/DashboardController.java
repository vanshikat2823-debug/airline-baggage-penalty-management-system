package com.vanshika.airlinebaggage.controller;

import com.vanshika.airlinebaggage.dto.DashboardSummary;
import com.vanshika.airlinebaggage.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/summary")
    public DashboardSummary getDashboardSummary() {
        return dashboardService.getDashboardSummary();
    }
}