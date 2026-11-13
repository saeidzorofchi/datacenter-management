package com.zorofchi.datacenter.repository;

import com.zorofchi.datacenter.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment , Long> {
}
