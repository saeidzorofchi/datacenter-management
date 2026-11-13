package com.zorofchi.datacenter.service;

import com.zorofchi.datacenter.repository.EquipmentRepository;
import org.springframework.stereotype.Service;
import com.zorofchi.datacenter.entity.Rack;
import com.zorofchi.datacenter.entity.Equipment;

import java.util.List;

@Service
public class EquipmentService {

    private EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public Equipment addEquipment(Equipment equipment) {

        validateRackCapacity(equipment);

        validateUnitOverlap(equipment);

        return equipmentRepository.save(equipment);
    }

    public void validateRackCapacity(Equipment equipment) {

        Rack rack = equipment.getRack();

        if (rack == null) {
            throw new RuntimeException("Rack is required");
        }

        if (equipment.getStartUnit() < 1 || equipment.getHeightUnits() < 1) {
            throw new RuntimeException("Invalid equipment position");
        }

        int lastUnit = equipment.getStartUnit()
                + equipment.getHeightUnits() - 1;

        if (lastUnit > rack.getTotalUnits()) {
            throw new RuntimeException("Equipment exceeds rack capacity");
        }
    }

    public void validateUnitOverlap(Equipment equipment) {
        Rack rack = equipment.getRack();

        int newStart = equipment.getStartUnit();
        int newEnd = newStart + equipment.getHeightUnits() - 1;

        List<Equipment> existingEquipments =
                equipmentRepository.findByRackId(rack.getId());

        for (Equipment existing : existingEquipments) {
            int existingStart = existing.getStartUnit();
            int existingEnd = existingStart + existing.getHeightUnits() - 1;

            if (newStart <= existingEnd && newEnd >= existingStart) {
                throw new RuntimeException("Equipment units overlap");
            }
        }
    }
    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    public Equipment getEquipmentById(Long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));
    }

    public Equipment updateEquipment(Long id, Equipment newEquipment) {

        Equipment existing = getEquipmentById(id);

        existing.setName(newEquipment.getName());
        existing.setType(newEquipment.getType());
        existing.setDescription(newEquipment.getDescription());

        return equipmentRepository.save(existing);
    }

    public void deleteEquipment(Long id) {
        Equipment equipment = getEquipmentById(id);
        equipmentRepository.delete(equipment);
    }


}
