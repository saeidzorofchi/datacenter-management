package com.zorofchi.datacenter.controller;

import com.zorofchi.datacenter.entity.Rack;
import com.zorofchi.datacenter.service.RackService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/racks")
public class RackController {

    private final RackService rackService;

    public RackController(RackService rackService) {
        this.rackService = rackService;
    }

    @PostMapping
    public Rack addRack(@RequestBody Rack rack) {
        return rackService.addRack(rack);
    }

    @GetMapping
    public List<Rack> getAllRacks() {
        return rackService.getAllRacks();
    }

    @GetMapping("/{id}")
    public Rack getRackById(@PathVariable Long id) {
        return rackService.getRackById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRack(@PathVariable Long id) {
        rackService.deleteRack(id);
    }
}