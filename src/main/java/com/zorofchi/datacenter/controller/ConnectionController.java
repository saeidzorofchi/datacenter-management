package com.zorofchi.datacenter.controller;

import com.zorofchi.datacenter.entity.Connection;
import com.zorofchi.datacenter.service.ConnectionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/connections")
public class ConnectionController {

    private final ConnectionService connectionService;

    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @PostMapping
    public Connection addConnection(@RequestBody Connection connection) {
        return connectionService.addConnection(connection);
    }

    @GetMapping
    public List<Connection> getAllConnections() {
        return connectionService.getAllConnections();
    }

    @GetMapping("/{id}")
    public Connection getConnectionById(@PathVariable Long id) {
        return connectionService.getConnectionById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteConnection(@PathVariable Long id) {
        connectionService.deleteConnection(id);
    }
}