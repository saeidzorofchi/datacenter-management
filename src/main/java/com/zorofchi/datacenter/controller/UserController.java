package com.zorofchi.datacenter.controller;

import com.zorofchi.datacenter.entity.User;
import com.zorofchi.datacenter.entity.UserRole;
import com.zorofchi.datacenter.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
    
    @PutMapping("/{id}/role")
    public User updateUserRole(@PathVariable Long id
            ,@RequestBody UserRole userRole){
        return userService.updateUserRole(id,userRole);


    }


}
