package com.zorofchi.datacenter.repository;

import com.zorofchi.datacenter.entity.DataCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataCenterRepository extends JpaRepository<DataCenter , Long> {
}
