package com.zorofchi.datacenter.service;

import com.zorofchi.datacenter.entity.ActivityLog;
import com.zorofchi.datacenter.repository.ActivityLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityLogService {

    private final ActivityLogRepository activityLogRepository;


    public ActivityLogService(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    public ActivityLog logActivity(ActivityLog activityLog){
        return activityLogRepository.save(activityLog);
    }

    public List<ActivityLog> getAllActivities(){
        return activityLogRepository.findAll();

    }



}















