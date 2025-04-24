package com.kbf.management.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kbf.management.model.Provender;
import com.kbf.management.service.ProvenderService;

import java.util.List;

@RestController
@RequestMapping("/api/provenders")
public class ProvenderController {

    @Autowired
    private ProvenderService provenderService;

    @GetMapping
    public List<Provender> getAllProvenders() {
        return provenderService.getAllProvenders();
    }

    @PostMapping
    public Provender createProvender(@RequestBody Provender provender) {
        return provenderService.saveProvender(provender);
    }
}
