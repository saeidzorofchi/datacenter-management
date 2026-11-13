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

        Long sourcePortId = connection.getSourcePort().getId();
        Long destinationPortId = connection.getDestinationPort().getId();

        if (sourcePortId.equals(destinationPortId)) {
            throw new RuntimeException("A port cannot connect to itself");
        }

        if (connectionRepository.existsBySourcePortId(sourcePortId) ||
                connectionRepository.existsByDestinationPortId(sourcePortId)) {
            throw new RuntimeException("Source port is already connected");
        }

        if (connectionRepository.existsBySourcePortId(destinationPortId) ||
                connectionRepository.existsByDestinationPortId(destinationPortId)) {
            throw new RuntimeException("Destination port is already connected");
        }

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