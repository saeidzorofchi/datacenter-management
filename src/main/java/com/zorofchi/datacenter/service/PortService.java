package com.zorofchi.datacenter.service;

import com.zorofchi.datacenter.entity.Equipment;
import com.zorofchi.datacenter.entity.Port;
import com.zorofchi.datacenter.repository.EquipmentRepository;
import com.zorofchi.datacenter.repository.PortRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortService {


    private final PortRepository portRepository;
    private final EquipmentRepository equipmentRepository;

    public PortService(PortRepository portRepository,
                       EquipmentRepository equipmentRepository) {
        this.portRepository = portRepository;
        this.equipmentRepository = equipmentRepository;
    }

    public Port addPort(Port port) {

        Equipment equipment = equipmentRepository.findById(port.getEquipment().getId())
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        if (equipment.getType().equals("PATCH_PANEL") && port.getSide() == null) {
            throw new RuntimeException("PatchPanel port side is required");
        }

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