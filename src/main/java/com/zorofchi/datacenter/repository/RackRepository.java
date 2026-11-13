package com.zorofchi.datacenter.repository;

import com.zorofchi.datacenter.entity.Rack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RackRepository extends JpaRepository<Rack , Long> {
}
