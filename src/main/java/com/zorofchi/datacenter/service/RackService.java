package com.zorofchi.datacenter.service;

import com.zorofchi.datacenter.entity.Rack;
import com.zorofchi.datacenter.repository.RackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RackService {

    private final RackRepository rackRepository;

    public RackService(RackRepository rackRepository) {
        this.rackRepository = rackRepository;
    }

    public Rack addRack(Rack rack) {
        return rackRepository.save(rack);
    }

    public List<Rack> getAllRacks() {
        return rackRepository.findAll();
    }

    public Rack getRackById(Long id) {
        return rackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rack not found"));
    }

    public void deleteRack(Long id) {
        Rack rack = getRackById(id);
        rackRepository.delete(rack);
    }
}