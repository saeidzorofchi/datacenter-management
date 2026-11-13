package com.zorofchi.datacenter.service;

import com.zorofchi.datacenter.entity.Connection;
import com.zorofchi.datacenter.repository.ConnectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionService {

    private final ConnectionRepository connectionRepository;

    public ConnectionService(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    public Connection addConnection(Connection connection) {
        return connectionRepository.save(connection);
    }

    public List<Connection> getAllConnections() {
        return connectionRepository.findAll();
    }

    public Connection getConnectionById(Long id) {
        return connectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Connection not found"));
    }

    public void deleteConnection(Long id) {
        Connection connection = getConnectionById(id);
        connectionRepository.delete(connection);
    }
}