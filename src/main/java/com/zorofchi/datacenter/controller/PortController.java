package com.zorofchi.datacenter.controller;

import com.zorofchi.datacenter.entity.Port;
import com.zorofchi.datacenter.service.PortService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ports")
public class PortController {

    private final PortService portService;

    public PortController(PortService portService) {
        this.portService = portService;
    }

    @PostMapping
    public Port addPort(@RequestBody Port port) {
        return portService.addPort(port);
    }

    @GetMapping
    public List<Port> getAllPorts() {
        return portService.getAllPorts();
    }

    @GetMapping("/{id}")
    public Port getPortById(@PathVariable Long id) {
        return portService.getPortById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePort(@PathVariable Long id) {
        portService.deletePort(id);
    }
}