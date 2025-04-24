package com.kbf.management.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kbf.management.model.Pond;
import com.kbf.management.service.PondService;

import java.util.List;

@RestController
@RequestMapping("/api/ponds")
public class PondController {

    @Autowired
    private PondService pondService;

    @GetMapping
    public List<Pond> getAllPonds() {
        return pondService.getAllPonds();
    }

    @PostMapping
    public Pond createPond(@RequestBody Pond pond) {
        return pondService.savePond(pond);
    }
}
