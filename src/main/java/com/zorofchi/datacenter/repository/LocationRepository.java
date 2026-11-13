package com.zorofchi.datacenter.repository;

import com.zorofchi.datacenter.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location , Long> {
}
