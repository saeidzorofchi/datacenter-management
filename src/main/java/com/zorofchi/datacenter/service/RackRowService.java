package com.zorofchi.datacenter.service;

import com.zorofchi.datacenter.entity.RackRow;
import com.zorofchi.datacenter.repository.RackRowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RackRowService {

    private final RackRowRepository rackRowRepository;

    public RackRowService(RackRowRepository rackRowRepository) {
        this.rackRowRepository = rackRowRepository;
    }

    public RackRow addRackRow(RackRow rackRow) {
        return rackRowRepository.save(rackRow);
    }

    public List<RackRow> getAllRackRows() {
        return rackRowRepository.findAll();
    }

    public RackRow getRackRowById(Long id) {
        return rackRowRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RackRow not found"));
    }

    public void deleteRackRow(Long id) {
        RackRow rackRow = getRackRowById(id);
        rackRowRepository.delete(rackRow);
    }
}