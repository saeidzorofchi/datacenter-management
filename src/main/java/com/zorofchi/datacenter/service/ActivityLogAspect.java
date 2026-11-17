package com.zorofchi.datacenter.service;

import com.zorofchi.datacenter.entity.ActivityLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
            "(execution(* com.zorofchi.datacenter.service.*.*(..)) || " +
                    "execution(* com.zorofchi.customer.service.*.*(..))) && " +
                    "!within(com.zorofchi.datacenter.service.ActivityLogService) && " +
                    "!within(com.zorofchi.datacenter.service.CustomUserDetailsService)"
    )
    public void logActivity(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String action = className + " - " + methodName;
        ActivityLog activityLog = new ActivityLog();
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = "UNKNOWN";

        if (authentication != null && authentication.isAuthenticated()
                && !"anonymousUser".equals(authentication.getName())) {
            username = authentication.getName();
        }

        activityLog.setUsername(username);        activityLog.setAction(action);
        activityLog.setTimestamp(LocalDateTime.now());
        activityLogService.logActivity(activityLog);




    }




}
