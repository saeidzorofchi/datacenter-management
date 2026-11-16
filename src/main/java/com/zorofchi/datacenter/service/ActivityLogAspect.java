package com.zorofchi.datacenter.service;

import com.zorofchi.datacenter.entity.ActivityLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

// Automatically logs add, update and delete operations
@Aspect
@Component
public class ActivityLogAspect {
    private final ActivityLogService activityLogService;


    public ActivityLogAspect(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    // Saves activity details after successful execution
    @AfterReturning(
            "execution(* com.zorofchi.datacenter.service.*.add*(..)) || " +
                    "execution(* com.zorofchi.datacenter.service.*.update*(..)) || " +
                    "execution(* com.zorofchi.datacenter.service.*.delete*(..)) || " +
                    "execution(* com.zorofchi.datacenter.service.*.get*(..))"
    )
    public void logActivity(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String action = className + " - " + methodName;
        ActivityLog activityLog = new ActivityLog();
        activityLog.setUsername("UNKNOWN");
        activityLog.setAction(action);
        activityLog.setTimestamp(LocalDateTime.now());
        activityLogService.logActivity(activityLog);




    }




}
