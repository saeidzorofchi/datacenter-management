package com.zorofchi.datacenter.repository;

import com.zorofchi.datacenter.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityLogRepository extends JpaRepository<ActivityLog , Long> {
}
