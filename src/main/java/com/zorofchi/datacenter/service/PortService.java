package com.zorofchi.datacenter.service;

import com.zorofchi.datacenter.entity.Port;
import com.zorofchi.datacenter.repository.PortRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortService {

    private final PortRepository portRepository;

    public PortService(PortRepository portRepository) {
        this.portRepository = portRepository;
    }

    public Port addPort(Port port) {
        return portRepository.save(port);
    }

    public List<Port> getAllPorts() {
        return portRepository.findAll();
    }

    public Port getPortById(Long id) {
        return portRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Port not found"));
    }

    public void deletePort(Long id) {
        Port port = getPortById(id);
        portRepository.delete(port);
    }
}