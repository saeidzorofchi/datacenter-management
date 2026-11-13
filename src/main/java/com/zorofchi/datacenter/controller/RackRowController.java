package com.zorofchi.datacenter.controller;

import com.zorofchi.datacenter.entity.RackRow;
import com.zorofchi.datacenter.service.RackRowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rack-rows")
public class RackRowController {

    private final RackRowService rackRowService;

    public RackRowController(RackRowService rackRowService) {
        this.rackRowService = rackRowService;
    }

    @PostMapping
    public RackRow addRackRow(@RequestBody RackRow rackRow) {
        return rackRowService.addRackRow(rackRow);
    }

    @GetMapping
    public List<RackRow> getAllRackRows() {
        return rackRowService.getAllRackRows();
    }

    @GetMapping("/{id}")
    public RackRow getRackRowById(@PathVariable Long id) {
        return rackRowService.getRackRowById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRackRow(@PathVariable Long id) {
        rackRowService.deleteRackRow(id);
    }
}