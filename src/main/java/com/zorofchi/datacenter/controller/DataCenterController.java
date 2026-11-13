package com.zorofchi.datacenter.controller;

import com.zorofchi.datacenter.entity.DataCenter;
import com.zorofchi.datacenter.service.DataCenterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/datacenters")
public class DataCenterController {

    private final DataCenterService dataCenterService;

    public DataCenterController(DataCenterService dataCenterService) {
        this.dataCenterService = dataCenterService;
    }

    @PostMapping
    public DataCenter addDataCenter(@RequestBody DataCenter dataCenter) {
        return dataCenterService.addDataCenter(dataCenter);
    }

    @GetMapping
    public List<DataCenter> getAllDataCenters() {
        return dataCenterService.getAllDataCenters();
    }

    @GetMapping("/{id}")
    public DataCenter getDataCenterById(@PathVariable Long id) {
        return dataCenterService.getDataCenterById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDataCenter(@PathVariable Long id) {
        dataCenterService.deleteDataCenter(id);
    }
}