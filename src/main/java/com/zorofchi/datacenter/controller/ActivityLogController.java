package com.zorofchi.datacenter.controller;

import com.zorofchi.datacenter.entity.ActivityLog;
import com.zorofchi.datacenter.service.ActivityLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity-logs")
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    public ActivityLogController(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    @GetMapping
    public List<ActivityLog> getAllActivities(){
        return activityLogService.getAllActivities();
    }

    @PostMapping
    public ActivityLog addActivity(@RequestBody ActivityLog activityLog){
        return activityLogService.logActivity(activityLog);
    }




}
